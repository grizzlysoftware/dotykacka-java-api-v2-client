package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseStockUp extends WarehouseOperation<StockUpItem> {
    @JsonProperty("_supplierId")
    public Long supplierId;
}
