package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.Address
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.Customer
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class CustomerServiceFacadeTest extends GenericDotykackaServiceFacadeTest<CustomerServiceFacade> {

    @Override
    Function<DotykackaApiClient, CustomerServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.customerServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return customer()
    }

    @Override
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Customer", "getById",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().getCustomer(obj.id) },
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getCustomers(1, 100, null)}
    }

    @Override
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Customer", "create",
                { _void -> customer() },
                service()::createCustomer,
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Customer", "delete",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().deleteCustomer(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Customer", "update",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().updateCustomer(obj)},
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Customer", "patch",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().patchCustomer(obj) },
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    def customer() {
        def object = new Customer()
        object.points = 201
        object.email = "TEST_CUSTOMER@dotykacka.cz"
        object.firstName = "TEST_CUSTOMER firstName"
        object.lastName = "TEST_CUSTOMER lastName"
        object.barcode = "666006661"
        object.discountGroupId = longVar("DISCOUNT_GROUP_ID", 5L)
        object.address = new Address()
        object.address.addressLine1 = ""
        object.address.addressLine2 = ""
        object.address.zipCode = ""
        object.address.country = ""
        return object
    }
}
