package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class CloudEntity extends Entity {
    @JsonProperty("_cloudId")
    public Long cloudId;

    public String etag;

    @JsonProperty("externalId")
    public String externalId;

    @JsonProperty("modifiedBy")
    public String modifiedBy;

    @JsonProperty("versionDate")
    public ZonedDateTime modifiedAt;

    @JsonProperty("created")
    public ZonedDateTime createdAt;
}
