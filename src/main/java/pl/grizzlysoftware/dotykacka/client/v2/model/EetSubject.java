package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EetSubject extends CloudEntity {

    @JsonProperty("vatId")
    public String vatId = "";

    @JsonProperty("enabled")
    public Boolean isEnabled = false;

    @JsonProperty("vatPayer")
    public Boolean isVatPayer = false;
}
