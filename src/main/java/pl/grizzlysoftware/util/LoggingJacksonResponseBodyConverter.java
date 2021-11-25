/*
 * Copyright (c) 2021 Grizzly Software, https://grizzlysoftware.pl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

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
