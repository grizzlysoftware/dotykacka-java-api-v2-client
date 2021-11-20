package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class DiscountGroup extends CloudEntity {

    @JsonProperty("discountPercent")
    public BigDecimal discountPercent;

    @JsonProperty("display")
    public Boolean isDisplayed = true;

}
