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
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Warehouse", "getById",
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
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Warehouse", "create",
                { _void -> warehouse() },
                service()::createWarehouse,
                { obj -> service().deleteWarehouse(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Warehouse", "delete",
                { _void -> service().createWarehouse(warehouse()) },
                { obj -> service().deleteWarehouse(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Warehouse", "update",
                { _void -> service().createWarehouse(warehouse()) },
                { obj -> service().updateWarehouse(obj)},
                { obj -> service().deleteWarehouse(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Warehouse", "patch",
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
