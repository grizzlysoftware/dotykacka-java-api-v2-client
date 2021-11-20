package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
    @JsonProperty("email")
    public String email;

    @JsonProperty("phone")
    public String phoneNumber;
}
