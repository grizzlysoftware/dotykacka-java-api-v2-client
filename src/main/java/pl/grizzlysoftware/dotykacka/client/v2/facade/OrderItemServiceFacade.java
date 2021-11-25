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
