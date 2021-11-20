package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class DeliveryNote extends BranchEntity {

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("documentId")
    public String documentId;

    @JsonProperty("documentNumber")
    public String documentNumber;

    @JsonProperty("expeditionDate")
    public ZonedDateTime expeditionDate;

    @JsonProperty("status")
    public Integer status;

    @JsonProperty("supplierName")
    public String supplierName;

    @JsonProperty("text")
    public String text;

    @JsonProperty("type")
    public Type type;

    @JsonProperty("url")
    public String url;

    public enum Type {
        DELIVERY_NOTE,
        RETURN
    }
}
