package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ProductIngredient extends CloudEntity {
    @JsonProperty("_productId")
    public Long ingredientId;

    @JsonProperty("_parentProductId")
    public Long productId;

    @JsonProperty("quantity")
    public BigDecimal quantity;

    @JsonProperty("unit")
    public Unit unit;
}
