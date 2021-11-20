package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class Branch extends CloudEntity {
    @JsonProperty("display")
    public Boolean shouldBeDisplayed = true;

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("features")
    public Long features = 0L;
}
