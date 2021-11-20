package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag extends CloudEntity {
    @JsonProperty("name")
    public String name;
}
