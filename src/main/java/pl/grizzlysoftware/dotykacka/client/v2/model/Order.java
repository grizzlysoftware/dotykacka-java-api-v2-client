package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class Order extends BranchEntity {

    @JsonProperty("_courseId")
    public Long courseId;

    @JsonProperty("_customerId")
    public Long customerId;

    @JsonProperty("_eetSubjectId")
    public Long eetSubjectId;

    @JsonProperty("_relatedInvoiceId")
    public Long invoiceId;

    @JsonProperty("_sellerId")
    public Long sellerId;

    @JsonProperty("_sourceOrderId")
    public Long sourceOrderId;

    @JsonProperty("_tableId")
    public Long tableId;

    @JsonProperty("documentNumber")
    public String documentNumber;

    @JsonProperty("documentType")
    public DocumentType documentType;

    @JsonProperty("itemCount")
    public Long itemCount;

    @JsonProperty("totalValueRounded")
    public BigDecimal totalValue;

    @JsonProperty("points")
    public Double points = 0.0;

    @JsonProperty("paid")
    public Boolean isPaid = false;

    @JsonProperty("parked")
    public Boolean isParked = false;

    @JsonProperty("completed")
    public ZonedDateTime completedAt;

    @JsonProperty("canceledDate")
    public ZonedDateTime canceledAt;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    @JsonProperty("orderItems")
    public Collection<OrderItem> orderItems = new HashSet<>();

    @JsonProperty("moneyLogs")
    public Collection<MoneyLog> moneyLogs = new HashSet<>();

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("locationAccuracy")
    public Double locationAccuracy;

    @JsonProperty("locationDate")
    public ZonedDateTime locatedAt;

    @JsonProperty("locationLatitude")
    public Double locationLatitude;

    @JsonProperty("locationLongitude")
    public Double locationLongitude;

    @JsonProperty("note")
    public String note = "";

    @JsonProperty("printData")
    public String printData = "";

    @JsonProperty("merchantPrintData")
    public String merchantPrintData = "";

    @JsonProperty("bkp")
    public String bkp = "";

    @JsonProperty("pkp")
    public String pkp = "";

    @JsonProperty("fik")
    public String fik = "";
}
