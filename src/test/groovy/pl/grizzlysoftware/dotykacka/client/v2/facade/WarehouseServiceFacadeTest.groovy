package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.Warehouse
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class WarehouseServiceFacadeTest extends GenericDotykackaServiceFacadeTest<WarehouseServiceFacade> {

    @Override
    Function<DotykackaApiClient, WarehouseServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.warehouseServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return warehouse()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Warehouse", "getById",
                { _void -> service().createWarehouse(warehouse()) },
                { obj -> service().getWarehouse(obj.id) },
                { obj -> service().deleteWarehouse(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getWarehouses(1, 100, null, null)}
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Warehouse", "create",
                { _void -> warehouse() },
                service()::createWarehouse,
                { obj -> service().deleteWarehouse(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Warehouse", "delete",
                { _void -> service().createWarehouse(warehouse()) },
                { obj -> service().deleteWarehouse(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Warehouse", "update",
                { _void -> service().createWarehouse(warehouse()) },
                { obj -> service().updateWarehouse(obj)},
                { obj -> service().deleteWarehouse(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Warehouse", "batch update",
                { _void -> service().getWarehouses(1, 2, null) },
                { obj ->
                    service().updateWarehouses(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Warehouse", "patch",
                { _void -> service().createWarehouse(warehouse()) },
                { obj -> service().patchWarehouse(obj) },
                { obj -> service().deleteWarehouse(obj.id) }
        )
    }

    static def warehouse() {
        def object = new Warehouse()
        object.barCode = "23512234123"
        object.name = "TEST_WAREHOUSE"
        return object
    }
}
