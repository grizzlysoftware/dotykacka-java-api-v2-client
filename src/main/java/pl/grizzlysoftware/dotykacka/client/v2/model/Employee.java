package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Bartosz Pawłowski on 2019-07-01.
 */
public class Employee extends CloudEntity {

    @JsonProperty("accessLevel")
    public Long accessLevel = 0L;

    @JsonProperty("stockAccessLevel")
    public Long stockAccessLevel = 0L;

    @JsonProperty("barcode")
    public String barcode = "";

    @JsonProperty("email")
    public String email;

    @JsonProperty("hexColor")
    public String hexColor = "#000000";;

    @JsonProperty("enabled")
    public Boolean isEnabled = true;

    @JsonProperty("phone")
    public String phone;

    @JsonProperty("requirePinAlways")
    public Boolean isPinAlwaysRequired = true;

    @JsonProperty("maxDiscount")
    public Double maxDiscount = 0.0d;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();
}
