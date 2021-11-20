package pl.grizzlysoftware.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class LoggingJacksonResponseBodyConverter implements Converter<ResponseBody, Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingJacksonResponseBodyConverter.class);

    protected ObjectMapper mapper;
    protected Type type;

    public LoggingJacksonResponseBodyConverter(ObjectMapper mapper, Type type) {
        this.mapper = mapper;
        this.type = type;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        final var javaType = mapper.getTypeFactory().constructType(type);
        final var reader = mapper.readerFor(javaType);
        final var str = value.string();
        LOGGER.debug("Converting: {} body: '{}'", javaType.getRawClass().getSimpleName(), str);
        if (isEmpty(str)) {
            return null;
        }
        return reader.readValue(str);
    }
}
