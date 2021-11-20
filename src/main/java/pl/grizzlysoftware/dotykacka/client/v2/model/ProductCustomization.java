package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ProductCustomization extends CloudEntity {

    @JsonProperty("_categoryId")
    public Long categoryId;

    @JsonProperty("_productId")
    public Long productId;

    @JsonProperty("_defaultProductIds")
    public Collection<String> defaultProductIds = new HashSet<>();

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("minSelected")
    public Integer minSelected;

    @JsonProperty("maxSelected")
    public Integer maxSelected;

    @JsonProperty("sortOrder")
    public Long sortOrder;

    @JsonProperty("priceLevel")
    public String priceLevel;

}
