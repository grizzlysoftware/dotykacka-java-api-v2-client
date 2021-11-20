package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class StockUpItem extends StockTransferItem {
    @JsonProperty("purchasePrice")
    public BigDecimal sellPrice;
}
