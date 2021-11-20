package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class MoneyLog extends BranchEntity {

    @JsonProperty("_employeeId")
    public Long employeeId;

    @JsonProperty("_orderId")
    public Long orderId;

    @JsonProperty("_sellerId")
    public Long sellerId;

    @JsonProperty("paymentTypeId")
    public Long paymentTypeId;

    @JsonProperty("transactionType")
    public TransactionType transactionType;

    @JsonProperty("amount")
    public BigDecimal amount;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("note")
    public String note = "";
}
