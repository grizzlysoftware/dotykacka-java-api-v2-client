package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class WarehouseOperation<T> {
    @JsonIgnore
    public Long warehouseId;

    @JsonProperty("invoiceNumber")
    public String invoiceNumber;

    @JsonProperty("note")
    public String note;

    @JsonProperty("updatePurchasePrice")
    public Boolean updateProductPurchasePrice;

    @JsonProperty("items")
    public Collection<T> items;
}
