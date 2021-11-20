package pl.grizzlysoftware.dotykacka.util;

import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity;
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
