package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class StockTransferItem {
    @JsonProperty("_productId")
    public Long productId;

    @JsonProperty("externalId")
    public String externalId;

    @JsonProperty("purchasePrice")
    public BigDecimal purchasePrice;

    @JsonProperty("quantity")
    public BigDecimal quantity;
}
