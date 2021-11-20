package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Employee;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.EmployeeService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class EmployeeServiceFacade extends DotykackaApiService<EmployeeService> {
    public EmployeeServiceFacade(EmployeeService service) {
        super(service);
    }

    public Employee createEmployee(Employee employee) {
        return createEmployees(singletonList(employee))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Employee> createEmployees(Collection<Employee> employees) {
        return execute(service.createEmployees(employees));
    }

    public Employee updateEmployee(Employee employee) {
        return execute(service.updateEmployee(employee.id, employee));
    }

    public Employee patchEmployee(Employee employee) {
        return execute(service.patchEmployee(employee.id, employee));
    }

    public Employee deleteEmployee(Long employeeId) {
        return execute(service.deleteEmployee(employeeId));
    }

    public Employee getEmployee(Long id) {
        return execute(service.getEmployee(id));
    }

    public ResultPage<Employee> getEmployees(int page, int pageSize, String filter, String sort) {
        return execute(service.getEmployees(page, pageSize, filter, sort));
    }

    public ResultPage<Employee> getEmployees(int page, int pageSize, String sort) {
        return getEmployees(page, pageSize, null, sort);
    }

    public Collection<Employee> getAllEmployees(String sort) {
        return batchLoader.load(page -> getEmployees(page.page, page.pageSize, sort));
    }

    public Collection<Employee> getAllEmployees() {
        return getAllEmployees(null);
    }
}
