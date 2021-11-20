package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cloud extends Entity {

    @JsonProperty("1ClickId")
    public String oneClickId;

    @JsonProperty("_companyId")
    public String companyId;

    @JsonProperty("country")
    public String country;

    @JsonProperty("expired")
    public Boolean isExpired = false;

    @JsonProperty("restricted")
    public Boolean isRestricted = false;

    @JsonProperty("segment")
    public String segment = "";
}
