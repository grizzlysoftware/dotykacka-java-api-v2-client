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

import com.fasterxml.jackson.databind.JsonMappingException;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken;
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity;
import pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions;
import pl.grizzlysoftware.dotykacka.util.exception.IdNullPointerException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class RetrofitCallExecutor implements OnRetrofitCallExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetrofitCallExecutor.class);
    private static final String EXCEPTIONAL_MESSAGE_PATTERN = "Unable to invoke external service: '%s'" +
            "request url: '%s'%n" +
            "request method: '%s'%n" +
            "request headers: '%s'%n" +
            "request body: %n'%s'%n" +
            "response status: '%s'%n" +
            "response message: '%s'%n" +
            "response headers: %n'%s'%n" +
            "response body: %n'%s'";

    protected final Class<?> target;

    protected OnRetrofitCallExecutionListener callExecutionListener = this;

    public RetrofitCallExecutor(Class<?> target) {
        this.target = requireNonNull(target);
    }
    public <T> T execOrThrow(Call<T> call) {
        return executeOrThrow(call)
                .body();
    }

    public <T> Response<T> executeOrThrow(Call<T> call) {
        try {
            callExecutionListener.onBeforeExecution(call);
            final var response = requireNonNull(call).execute();
            callExecutionListener.onAfterExecution(call, response);
            if (!response.isSuccessful()) {
                final var request = call.request();
                throw new ResponseStatusException(response.code(),
                        String.format(EXCEPTIONAL_MESSAGE_PATTERN,
                                target.getSimpleName(),
                                request.url().url().toString(),
                                request.method(),
                                request.headers().toString(),
                                requestBodyAsString(request),
                                response.code(),
                                response.message(),
                                response.headers().toString(),
                                ofNullable(response.errorBody()).map(this::unwrapBody).orElse("{}"))
                );
            }
            callExecutionListener.onExecutionSuccessful(call, response);
            return response;
        } catch (JsonMappingException e) {
            LOGGER.error("Exception occured while deserializing response: {}", e.getMessage());
            throw new ResponseStatusException(HttpURLConnection.HTTP_INTERNAL_ERROR);
        } catch (IOException e) {
            LOGGER.error("Exception occured while sending request: {}", e.getMessage());
            throw new ResponseStatusException(HttpURLConnection.HTTP_UNAVAILABLE);
        }
    }

    public void setCallExecutionListener(OnRetrofitCallExecutionListener callExecutionListener) {
        this.callExecutionListener = ExceptionPreconditions.checkNotNull(callExecutionListener, "20211118:213525", "OnRetrofitCallExecutionListener cannot be null");
    }

    private String requestBodyAsString(Request request) throws IOException {
        final var baos = new ByteArrayOutputStream();
        final var sink = new Buffer();
        final var body = request.body();
        if (body == null) {
            return "{}";
        } else {
            body.writeTo(sink);
            sink.copyTo(baos);
            return baos.toString(UTF_8);
        }
    }

    private String unwrapBody(ResponseBody body) {
        try {
            return body.string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void onBeforeExecution(Call<T> call) {

    }

    @Override
    public <T> void onAfterExecution(Call<T> call, Response<T> response) {

    }

    @Override
    public <T> void onExecutionSuccessful(Call<T> call, Response<T> response) {

    }
}
