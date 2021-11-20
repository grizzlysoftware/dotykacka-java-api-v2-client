package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class WarehouseItemSale {
    @JsonIgnore
    public Long warehouseId;

    @JsonProperty("items")
    public Collection<StockItemSale> items;
}
