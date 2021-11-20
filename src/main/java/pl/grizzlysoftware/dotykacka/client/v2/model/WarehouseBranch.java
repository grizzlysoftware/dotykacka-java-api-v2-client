package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class WarehouseBranch extends BranchEntity {

    @JsonProperty("_warehouseId")
    public String warehouseId;

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("visible")
    public Boolean isVisible = false;

    @JsonProperty("subscribed")
    public Boolean isSubscribed = false;

    @JsonProperty("sortOrder")
    public Long sortOrder = 0L;

    @JsonProperty("enabled")
    public Boolean isEnabled = true;
}
