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
