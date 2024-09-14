package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

public class Customer {
    @JsonProperty("barcode")
    public String barcode;
    @JsonProperty("_discountGroupId")
    public Long discountGroupId;
    @JsonProperty("_sellerId")
    public Long sellerGroupId;
    @JsonProperty("birthday")
    public String birthday;
    @JsonProperty("email")
    public String email;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonUnwrapped
    public Address address;
    @JsonProperty("companyId")
    public String companyId = "";
    @JsonProperty("companyName")
    public String companyName = "";
    @JsonProperty("vatId")
    public String vatId = "";
    @JsonProperty("internalNote")
    public String note = "";
    @JsonProperty("phone")
    public String phone = "";
    @JsonProperty("display")
    public Boolean shouldBeDisplayed = true;
    @JsonProperty("points")
    public Double points = 0.0;
    @JsonProperty("expireDate")
    public ZonedDateTime expiresAt;
    @JsonProperty("hexColor")
    public String hexColor = "#000000";
    @JsonProperty("headerPrint")
    public String headerPrint = "";
    @JsonProperty("tags")
    public Collection<String> tags = new HashSet();
    @JsonProperty("flags")
    public int flags = 0;
    @JsonProperty("deleted")
    public boolean deleted = false;

    public Customer() {
    }

}
