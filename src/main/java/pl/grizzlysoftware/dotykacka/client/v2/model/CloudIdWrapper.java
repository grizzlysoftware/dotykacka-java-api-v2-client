package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

public class CloudIdWrapper {
    private final Long cloudId;

    public CloudIdWrapper(Long cloudId) {
        this.cloudId = requireNonNull(cloudId, "cloudId cannot be null");
    }

    @JsonProperty("_cloudId")
    public Long getCloudId() {
        return cloudId;
    }
}
