package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.Address
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.Contact
import pl.grizzlysoftware.dotykacka.client.v2.model.Supplier
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function

class SupplierServiceFacadeTest extends GenericDotykackaServiceFacadeTest<SupplierServiceFacade> {

    @Override
    Function<DotykackaApiClient, SupplierServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.supplierServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return supplier()
    }

    @Override
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Supplier", "getById",
                { _void -> service().createSupplier(supplier()) },
                { obj -> service().getSupplier(obj.id) },
                { obj -> service().deleteSupplier(obj.id) }
        )
    }

    @Override
    java.util.function.Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getSuppliers(1, 100, null, null)}
    }

    @Override
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Supplier", "create",
                { _void -> supplier() },
                service()::createSupplier,
                { obj -> service().deleteSupplier(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Supplier", "delete",
                { _void -> service().createSupplier(supplier()) },
                { obj -> service().deleteSupplier(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Supplier", "update",
                { _void -> service().createSupplier(supplier()) },
                { obj -> service().updateSupplier(obj)},
                { obj -> service().deleteSupplier(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Supplier", "patch",
                { _void -> service().createSupplier(supplier()) },
                { obj -> service().patchSupplier(obj) },
                { obj -> service().deleteSupplier(obj.id) }
        )
    }

    def supplier() {
        def object = new Supplier()
        object.companyId = longVar("COMPANY_ID", 1L)
        object.name = "TEST_SUPPLIER"
        object.vatId = ""
        object.discountGroupId = longVar("DISCOUNT_GROUP_ID", 5L)
        object.address = new Address()
        object.contact = new Contact()
        object.contact.email = "TEST_SUPPLIER@dotykacka.cz"
        object.contact.phoneNumber = "+48555444333"
        return object
    }
}
