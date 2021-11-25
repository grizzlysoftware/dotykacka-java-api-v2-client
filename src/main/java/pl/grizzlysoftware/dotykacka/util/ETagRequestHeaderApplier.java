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

package pl.grizzlysoftware.dotykacka.util;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Interceptor;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import pl.grizzlysoftware.util.JacksonProvider;

import java.io.IOException;

import static java.util.Objects.isNull;

public class ETagRequestHeaderApplier implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        final var oldRequest = chain.request();
        final var body = oldRequest.body();
        if (isNull(body)) {
            return chain.proceed(oldRequest);
        }

        final var sink = new Buffer();
        body.writeTo(sink);

        //below fragment is not efficient but convenient
        final var deserializedBody = JacksonProvider.mapper.readTree(sink.inputStream());
        JsonNode entity;
        if (deserializedBody.isArray() && !deserializedBody.isEmpty()) {
            entity = deserializedBody.get(0);
        } else {
            entity = deserializedBody;
        }
        final var tagNode = entity.get("etag");
        var requestBuilder = oldRequest.newBuilder();
        if (tagNode != null) {
            final var etag = tagNode.asText();
            requestBuilder.header("If-Match", etag);
        }

        return chain.proceed(
                requestBuilder.build()
        );
    }
}
