/*
 * Copyright (c) 2021 Grizzly Software, https://grizzlysoftware.pl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

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
