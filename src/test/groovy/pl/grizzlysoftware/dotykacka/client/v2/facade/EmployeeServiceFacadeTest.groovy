package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.Employee
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class EmployeeServiceFacadeTest extends GenericDotykackaServiceFacadeTest<EmployeeServiceFacade> {

    @Override
    Function<DotykackaApiClient, EmployeeServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.employeeServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return employee()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Employee", "getById",
                { _void -> service().createEmployee(employee()) },
                { obj -> service().getEmployee(obj.id) },
                { obj -> service().deleteEmployee(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getEmployees(1, 100, null, null)}
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Employee", "create",
                { _void -> employee() },
                service()::createEmployee,
                { obj -> service().deleteEmployee(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Employee", "delete",
                { _void -> service().createEmployee(employee()) },
                { obj -> service().deleteEmployee(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Employee", "update",
                { _void -> service().createEmployee(employee()) },
                { obj -> service().updateEmployee(obj)},
                { obj -> service().deleteEmployee(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Employee", "batch update",
                { _void -> service().getEmployees(1, 5, null) },
                { obj ->
                    obj.data.forEach { it.hexColor = "#000000" }
                    service().updateEmployees(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Employee", "patch",
                { _void -> service().createEmployee(employee()) },
                { obj -> service().patchEmployee(obj) },
                { obj -> service().deleteEmployee(obj.id) }
        )
    }

    static def employee() {
        def object = new Employee()
        object.name = "TEST_EMPLOYEE"
        object.email = "TEST_EMPLOYEE@dotykacka.cz"
        object.phone = "+48555444333"
        return object
    }
}
