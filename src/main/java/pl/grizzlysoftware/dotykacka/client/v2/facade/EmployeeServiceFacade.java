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
    
    public Collection<Employee> updateEmployees(Collection<Employee> employees) {
        return execute(service.updateEmployees(employees));
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
