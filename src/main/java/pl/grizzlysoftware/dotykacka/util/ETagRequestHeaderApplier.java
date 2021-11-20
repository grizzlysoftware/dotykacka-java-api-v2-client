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
