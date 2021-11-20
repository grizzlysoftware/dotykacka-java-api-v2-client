package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseTransfer extends WarehouseOperation<StockTransferItem> {
    @JsonProperty("_originWarehouseId")
    public Long originWarehouseId;
}
