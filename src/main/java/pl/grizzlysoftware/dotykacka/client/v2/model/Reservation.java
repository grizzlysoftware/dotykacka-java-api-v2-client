package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class Reservation extends BranchEntity {

    @JsonProperty("_employeeId")
    public Long employeeId;

    @JsonProperty("_customerId")
    public Long customerId;

    @JsonProperty("_eetSubjectId")
    public Long eetSubjectId;

    @JsonProperty("_relatedInvoiceId")
    public Long invoiceId;

    @JsonProperty("_tableId")
    public Long tableId;

    @JsonProperty("itemCount")
    public Long itemCount = 0L;

    @JsonProperty("seats")
    public Short seatsCount;

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("status")
    public ReservationStatus status;

    @JsonProperty("startDate")
    public ZonedDateTime startsAt;

    @JsonProperty("endDate")
    public ZonedDateTime endsAt;

    @JsonProperty("note")
    public String note;
}
