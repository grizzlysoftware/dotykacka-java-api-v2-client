package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockItemSale extends StockTransferItem {
    @JsonProperty("_productId")
    public Long productId;

    @JsonProperty("externalId")
    public String externalId;

    @JsonProperty("quantity")
    public Double quantity;

    @JsonProperty("note")
    public String note;
}
