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

package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.OrderItem;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.OrderItemService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collection;

import static java.lang.String.format;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class OrderItemServiceFacade extends DotykackaApiService<OrderItemService> {
    private static final DateTimeFormatter RECEIPTS_RANGE_DATE_FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm:ss.SSSX").withZone(ZoneId.of("UTC"));
    private static final String RECEIPTS_RANGE_PATTERN = "completed|gteq|%s;completed|lt|%s";

    public OrderItemServiceFacade(OrderItemService service) {
        super(service);
    }

    public OrderItem getOrderItem(Long id) {
        return execute(service.getOrderItem(id));
    }

    public ResultPage<OrderItem> getOrderItems(int page, int pageSize, String filter, String sort) {
        return execute(service.getOrderItems(page, pageSize, filter, sort));
    }

    public ResultPage<OrderItem> getOrderItems(int page, int pageSize, String sort) {
        return getOrderItems(page, pageSize, null, sort);
    }

    public ResultPage<OrderItem> getOrderItemsForTimeRange(ZonedDateTime startDate, ZonedDateTime endDate, int page, int pageSize, String sort) {
        final var dateRange = format(RECEIPTS_RANGE_PATTERN, formatted(startDate), formatted(endDate));
        return execute(service.getOrderItems(page, pageSize, dateRange, sort));
    }

    public Collection<OrderItem> getAllOrderItemsForTimeRange(ZonedDateTime startDate, ZonedDateTime endDate, String sort) {
        return batchLoader.load(page -> getOrderItemsForTimeRange(startDate, endDate, page.page, page.pageSize, sort));
    }

    public Collection<OrderItem> getAllOrderItems(String sort) {
        return batchLoader.load(page -> getOrderItems(page.page, page.pageSize, sort));
    }

    public Collection<OrderItem> getAllOrderItems() {
        return getAllOrderItems(null);
    }

    private String formatted(ZonedDateTime zonedDateTime) {
        return RECEIPTS_RANGE_DATE_FORMATTER.format(zonedDateTime);
    }


}
