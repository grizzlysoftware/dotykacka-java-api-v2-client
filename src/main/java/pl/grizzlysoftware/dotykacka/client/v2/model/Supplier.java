package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Bartosz Pawłowski on 2019-07-01.
 */
public class Supplier extends CloudEntity {
    @JsonProperty("barcode")
    public String barcode = "";

    @JsonProperty("_discountGroupId")
    public Long discountGroupId;

    /**
     * field indicating company id, like REGON in Poland and  IČO in Czech Republic
     */
    @JsonProperty("companyId")
    public Long companyId;

    @JsonUnwrapped
    public Address address;

    @JsonUnwrapped
    public Contact contact;

    @JsonProperty("vatId")
    public String vatId;

    @JsonProperty("display")
    public Boolean shouldBeDisplayed = true;

    @JsonProperty("deliveryNoteIds")
    public String deliveryNoteIds = "";
}
