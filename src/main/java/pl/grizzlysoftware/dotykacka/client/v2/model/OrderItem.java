package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class OrderItem extends BranchEntity {

    @JsonProperty("_categoryId")
    public Long categoryId;

    @JsonProperty("_courseId")
    public Long courseId;

    @JsonProperty("_customerId")
    public Long customerId;

    @JsonProperty("_eetSubjectId")
    public Long eetSubjectId;

    @JsonProperty("_employeeId")
    public Long employeeId;

    @JsonProperty("_orderId")
    public Long orderId;

    @JsonProperty("_productId")
    public Long productId;

    @JsonProperty("_relatedOrderItemId")
    public Long orderItemId;

    @JsonProperty("_sellerId")
    public Long sellerId;

    @JsonProperty("alternativeName")
    public String alternativeName;

    @JsonProperty("name")
    public String name;

    @JsonProperty("subtitle")
    public String subtitle;

    @JsonProperty("ean")
    public Collection<String> eans = new HashSet<>();

    @JsonProperty("billedUnitPriceWithVat")
    public BigDecimal billedUnitPriceWithVat;

    @JsonProperty("billedUnitPriceWithoutVat")
    public BigDecimal billedUnitPriceWithoutVat;

    @JsonProperty("totalPriceWithVat")
    public BigDecimal totalPriceWithVat;

    @JsonProperty("totalPriceWithoutVat")
    public BigDecimal totalPriceWithoutVat;

    @JsonProperty("unit")
    public String unit;

    @JsonProperty("unitPriceWithVat")
    public BigDecimal unitPriceWithVat;

    @JsonProperty("unitPriceWithoutVat")
    public BigDecimal unitPriceWithoutVat;

    @JsonProperty("unitPurchasePrice")
    public BigDecimal unitPurchasePrice;

    @JsonProperty("vat")
    public BigDecimal vat;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("completed")
    public ZonedDateTime completedAt;

    @JsonProperty("canceledDate")
    public ZonedDateTime canceledAt;

    @JsonProperty("discountPercent")
    public BigDecimal discountPercent;

    @JsonProperty("discountPermitted")
    public Boolean isDiscountable;

    @JsonProperty("flags")
    public Integer flags = 0;

    @JsonProperty("tags")
    public Collection<String> tags = new HashSet<>();

    @JsonProperty("note")
    public String note;

    @JsonProperty("onSale")
    public Boolean isOnSale;

    @JsonProperty("packaging")
    public BigDecimal packaging;

    @JsonProperty("parked")
    public Boolean isParked;

    @JsonProperty("points")
    public BigDecimal points;

    @JsonProperty("quantity")
    public BigDecimal quantity;

    @JsonProperty("stockDeduct")
    public Boolean isStockDeductable;

    @JsonProperty("printData")
    public String printData;

    @JsonProperty("merchantPrintData")
    public String merchantPrintData;

    @JsonProperty("bkp")
    public String bkp;

    @JsonProperty("pkp")
    public String pkp;

    @JsonProperty("fik")
    public String fik;
}
