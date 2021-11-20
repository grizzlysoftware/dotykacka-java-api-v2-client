package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class Category extends CloudEntity {

    @JsonProperty("_eetSubjectId")
    public Long eetSubjectId;

    @JsonProperty("_defaultCourseId")
    public Long defaultCourseId;

    @JsonProperty("display")
    public Boolean isDisplayed = true;

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("hexColor")
    public String hexColor = "#000000";

    @JsonProperty("margin")
    public String margin;

    @JsonProperty("double")
    public Double maxDiscount;

    @JsonProperty("sortOrder")
    public Long sortOrder = 0L;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    @JsonProperty("vat")
    public Double vat;
}
