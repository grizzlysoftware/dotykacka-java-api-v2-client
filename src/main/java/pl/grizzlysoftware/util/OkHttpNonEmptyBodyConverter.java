package pl.grizzlysoftware.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Converter;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class OkHttpNonEmptyBodyConverter<T extends Object> implements Converter<ResponseBody, T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpNonEmptyBodyConverter.class);

    private Converter<ResponseBody, T> delegate;
    private TypeFactory typeFactory;

    public OkHttpNonEmptyBodyConverter(Converter<ResponseBody, T> delegate) {
        this.delegate = delegate;
        this.typeFactory = TypeFactory.defaultInstance();
    }

    @Override
    public T convert(ResponseBody body) throws JsonProcessingException {
        try {
            var out = delegate.convert(body);
            return out;
        } catch(InvalidFormatException e) {
            LOGGER.warn("cannot deserialize", e);
            return null;
        } catch (MismatchedInputException e) {
            //this is when api returns empty response, intead of empty list
            if (delegate instanceof LoggingJacksonResponseBodyConverter) {
                var conv = (LoggingJacksonResponseBodyConverter) delegate;
                var type = typeFactory.constructType(conv.type);
                if (type.isContainerType()) {
                    return conv.mapper.readValue("[]", type);
                } else {
                    return conv.mapper.readValue("{}", type);
                }
            } else {
                LOGGER.warn("cannot deserialize", e);
                return null;
            }
        } catch (Exception e) {
            LOGGER.warn("cannot deserialize", e);
            return null;
        }
    }
}
