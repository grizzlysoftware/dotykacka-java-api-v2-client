package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Bartosz Pawłowski on 2019-07-01.
 */
public class Table extends CloudEntity {

    @JsonProperty("_tableGroupId")
    public Long tableGroupId;

    @JsonProperty("_sellerId")
    public Long sellerId;

    @JsonProperty("enabled")
    public Boolean isEnabled = true;

    @JsonProperty("display")
    public Boolean shouldBeDisplayed = true;

    @JsonProperty("locationName")
    public String locationName;

    @JsonProperty("positionX")
    public Integer positionX;

    @JsonProperty("positionY")
    public Integer positionY;

    @JsonProperty("rotation")
    public Integer rotationAngle;

    @JsonProperty("seats")
    public Integer seatsCount;

    @JsonProperty("type")
    public Type type;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    public enum Type {
        SQUARE,
        SQUARE6,
        CIRCLE2,
        CIRCLE4,
        DELIVERY,
        CHAIR_SINGLE,
        ROUND,
        DOOR,
        GENERIC,
        CAR1,
        CAR2
    }
}
