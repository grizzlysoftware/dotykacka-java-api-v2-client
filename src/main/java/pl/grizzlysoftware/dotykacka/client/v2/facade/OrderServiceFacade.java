package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Order;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.OrderService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class OrderServiceFacade extends DotykackaApiService<OrderService> {

    public OrderServiceFacade(OrderService service) {
        super(service);
    }

    public Order getOrder(Long id) {
        return execute(service.getOrder(id));
    }

    public ResultPage<Order> getOrders(int page, int pageSize, String include, String namedFilter, String filter, String sort) {
        return execute(service.getOrders(page, pageSize, include, namedFilter, filter, sort));
    }

    public ResultPage<Order> getOrders(int page, int pageSize, String include, String filter, String sort) {
        return getOrders(page, pageSize, include, null, filter, sort);
    }

    public ResultPage<Order> getOrders(int page, int pageSize, String include, String sort) {
        return getOrders(page, pageSize, include, null, null, sort);
    }

    public ResultPage<Order> getOrdersIncludingMoneyLogs(int page, int pageSize, String filter, String sort) {
        return getOrders(page, pageSize, "moneyLogs", null, filter, sort);
    }

    public ResultPage<Order> getOrdersIncludingOrderItems(int page, int pageSize, String filter, String sort) {
        return getOrders(page, pageSize, "orderItems", null, filter, sort);
    }

    public ResultPage<Order> getOrdersIncludingMoneyLogsAndOrderItems(int page, int pageSize, String filter, String sort) {
        return getOrders(page, pageSize, "moneyLogs,orderItems", null, filter, sort);
    }

    public Collection<Order> getAllOrders(String include, String filter, String sort) {
        return batchLoader.load(page -> getOrders(page.page, page.pageSize, include, null, filter, sort));
    }

    public Collection<Order> getAllOrdersIncludingOrderItems(String filter, String sort) {
        return batchLoader.load(page -> getOrdersIncludingOrderItems(page.page, page.pageSize, filter, sort));
    }

    public Collection<Order> getAllOrdersIncludingMoneyLogs(String filter, String sort) {
        return batchLoader.load(page -> getOrdersIncludingMoneyLogs(page.page, page.pageSize, filter, sort));
    }

    public Collection<Order> getAllOrdersIncludingOrderItemsAndMoneyLogs(String filter, String sort) {
        return batchLoader.load(page -> getOrdersIncludingMoneyLogsAndOrderItems(page.page, page.pageSize, filter, sort));
    }

    public Collection<Order> getAllOrders(String sort) {
        return getAllOrders(null, null, sort);
    }

    public Collection<Order> getAllOrdersIncludingOrderItems(String sort) {
        return getAllOrdersIncludingOrderItems(null, sort);
    }

    public Collection<Order> getAllOrdersIncludingMoneyLogs(String sort) {
        return getAllOrdersIncludingMoneyLogs(null, sort);
    }

    public Collection<Order> getAllOrdersIncludingOrderItemsAndMoneyLogs(String sort) {
        return getAllOrdersIncludingOrderItemsAndMoneyLogs(null, sort);
    }

    public Collection<Order> getAllOrders() {
        return getAllOrders(null);
    }
}
