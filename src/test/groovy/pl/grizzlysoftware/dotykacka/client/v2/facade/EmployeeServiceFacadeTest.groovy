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
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Employee", "getById",
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
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Employee", "create",
                { _void -> employee() },
                service()::createEmployee,
                { obj -> service().deleteEmployee(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Employee", "delete",
                { _void -> service().createEmployee(employee()) },
                { obj -> service().deleteEmployee(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Employee", "update",
                { _void -> service().createEmployee(employee()) },
                { obj -> service().updateEmployee(obj)},
                { obj -> service().deleteEmployee(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Employee", "patch",
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
