package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    @JsonProperty("addressLine1")
    public String addressLine1 = "";

    @JsonProperty("addressLine2")
    public String addressLine2 = "";

    @JsonProperty("city")
    public String city = "";

    @JsonProperty("zip")
    public String zipCode = "";

    @JsonProperty("country")
    public String country = "";
}
