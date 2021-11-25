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

import pl.grizzlysoftware.dotykacka.client.v2.model.Customer;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.CustomerService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CustomerServiceFacade extends DotykackaApiService<CustomerService> {
    public CustomerServiceFacade(CustomerService service) {
        super(service);
    }

    public Customer createCustomer(Customer customer) {
        return createCustomers(singletonList(customer))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Customer> createCustomers(Collection<Customer> customers) {
        return execute(service.createCustomers(customers));
    }

    public Collection<Customer> updateCustomers(Collection<Customer> customers) {
        return execute(service.updateCustomers(customers));
    }

    public Customer updateCustomer(Customer customer) {
        return execute(service.updateCustomer(customer.id, customer));
    }

    public Customer patchCustomer(Customer customer) {
        return execute(service.patchCustomer(customer.id, customer));
    }

    public Customer deleteCustomer(Long customerId) {
        return execute(service.deleteCustomer(customerId));
    }

    public Customer getCustomer(Long id) {
        return execute(service.getCustomer(id));
    }

    public ResultPage<Customer> getCustomers(int page, int pageSize, String filter, String sort) {
        return execute(service.getCustomers(page, pageSize, filter, sort));
    }

    public ResultPage<Customer> getCustomers(int page, int pageSize, String sort) {
        return getCustomers(page, pageSize, null, sort);
    }

    public Collection<Customer> getAllCustomers(String sort) {
        return batchLoader.load(page -> getCustomers(page.page, page.pageSize, sort));
    }

    public Collection<Customer> getAllCustomers() {
        return getAllCustomers(null);
    }
}
