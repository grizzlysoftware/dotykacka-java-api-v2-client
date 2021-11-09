package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import static java.util.Objects.requireNonNull;

public class Cloud {
    private final Long cloudId;

    public Cloud(Long cloudId) {
        this.cloudId = requireNonNull(cloudId, "cloudId cannot be null");
    }

    @JsonAlias("_cloudId")
    public Long getCloudId() {
        return cloudId;
    }
}
