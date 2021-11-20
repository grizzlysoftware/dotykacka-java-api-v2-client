package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchEntity extends CloudEntity {
    @JsonProperty("_branchId")
    public Long branchId;
}
