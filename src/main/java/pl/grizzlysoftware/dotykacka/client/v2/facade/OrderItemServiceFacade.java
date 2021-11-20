package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.OrderItem;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.OrderItemService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static java.lang.String.format;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class OrderItemServiceFacade extends DotykackaApiService<OrderItemService> {
    private static final DateTimeFormatter RECEIPTS_RANGE_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
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
        final var dateRange = format(RECEIPTS_RANGE_PATTERN, RECEIPTS_RANGE_DATE_FORMATTER.format(startDate), RECEIPTS_RANGE_DATE_FORMATTER.format(endDate));
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
}
