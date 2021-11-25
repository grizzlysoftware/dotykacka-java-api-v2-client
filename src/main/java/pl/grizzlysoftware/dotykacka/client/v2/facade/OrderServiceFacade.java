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
