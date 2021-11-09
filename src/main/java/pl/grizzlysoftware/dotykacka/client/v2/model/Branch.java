package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class Branch {
    @JsonProperty("_cloudId")
    public Long cloudId;

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("created")
    public Long createdAt;

    @JsonProperty("deleted")
    public Boolean isDeleted;

    @JsonProperty("display")
    public Boolean shouldBeDisplayed;

    @JsonProperty("flags")
    public Integer flags;

    @JsonProperty("features")
    public Long features;

    @JsonProperty("versionDate")
    public LocalDateTime versionDate;

}
