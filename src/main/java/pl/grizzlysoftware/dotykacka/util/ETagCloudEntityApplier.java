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

import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.util.OnRetrofitCallExecutionListener;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Collection;

public class ETagCloudEntityApplier implements OnRetrofitCallExecutionListener {

    @Override
    public <T> void onBeforeExecution(Call<T> call) {
        //no-op
    }

    @Override
    public <T> void onAfterExecution(Call<T> call, Response<T> response) {
        //no-op
    }

    @Override
    public <T> void onExecutionSuccessful(Call<T> call, Response<T> response) {
        final var etag = response.headers().get("ETag");
        final var body = response.body();
        applyEtag(body, etag);
    }

    public <T> void applyEtag(T body, String etag) {
        etag = adjustEtag(etag);
        if (body instanceof Collection) {
            applyOnEntities(body, etag);
        } else if (body instanceof CloudEntity) {
            applyOnEntity(body, etag);
        } else if (body instanceof ResultPage) {
            applyOnEntities(((ResultPage<?>) body).data, etag);
        }
    }

    protected String adjustEtag(String etag) {
        if (etag == null) {
            return null;
        }

        return etag.replaceAll("\"", "");
    }

    protected void applyOnEntity(Object entity, String etag) {
        final var cloudEntity = (CloudEntity) entity;
        cloudEntity.etag = etag;
    }

    protected void applyOnEntities(Object entities, String etag) {
        final var cloudEntities = (Collection<?>) entities;
        cloudEntities
                .stream()
                .filter(CloudEntity.class::isInstance)
                .forEach(e -> applyOnEntity(e, etag));
    }


}
