package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductStock extends Product {
    @JsonProperty("_warehouseId")
    public Long warehouseId;

    @JsonProperty("purchasePriceWithoutVat")
    public BigDecimal purchasePriceWithoutVat;

    @JsonProperty("quantity")
    public BigDecimal quantity;
}
