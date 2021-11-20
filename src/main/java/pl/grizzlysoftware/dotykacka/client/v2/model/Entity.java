package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("deleted")
    public Boolean isDeleted = false;
}
