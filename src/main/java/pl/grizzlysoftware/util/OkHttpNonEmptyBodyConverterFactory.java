package pl.grizzlysoftware.util;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class OkHttpNonEmptyBodyConverterFactory extends Converter.Factory {
    @Override
    public Converter<ResponseBody, Object> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        var delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new OkHttpNonEmptyBodyConverter(delegate);
    }
}
