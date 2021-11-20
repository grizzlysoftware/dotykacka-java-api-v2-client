package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class Warehouse extends CloudEntity {

    @JsonProperty("hexColor")
    public String hexColor = "#000000";

    @JsonProperty("barCode")
    public String barCode;

    @JsonProperty("sortOrder")
    public Long sortOrder;

    @JsonProperty("enabled")
    public Boolean isEnabled = true;
}
