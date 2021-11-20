package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseBranch

import java.util.function.Function
import java.util.function.Supplier

class WarehouseBranchServiceFacadeTest extends GenericDotykackaServiceFacadeTest<WarehouseBranchServiceFacade> {

    @Override
    Function<DotykackaApiClient, WarehouseBranchServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.warehouseBranchServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return warehouseBranch()
    }

    @Override
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("WarehouseBranch", "getById",
                { _void -> service().createWarehouseBranch(warehouseBranch()) },
                { obj -> service().getWarehouseBranch(obj.id) },
                { obj -> null }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getWarehouseBranches(1, 100, null, null)}
    }

    @Override
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("WarehouseBranch", "create",
                { _void -> warehouseBranch() },
                service()::createWarehouseBranch,
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("WarehouseBranch", "delete",
                { _void -> service().createWarehouseBranch(warehouseBranch()) },
                { obj -> null },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("WarehouseBranch", "update",
                { _void -> service().createWarehouseBranch(warehouseBranch()) },
                { obj -> service().updateWarehouseBranch(obj)},
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("WarehouseBranch", "patch",
                { _void -> service().createWarehouseBranch(warehouseBranch()) },
                { obj -> service().patchWarehouseBranch(obj) },
                { obj -> null }
        )
    }

    def warehouseBranch() {
        def object = new WarehouseBranch()
        object.branchId = longVar("BRANCH_ID", 1L)
        object.warehouseId = longVar("WAREHOUSE_ID", 1L)
        object.name = "TEST_WAREHOUSE"
        return object
    }
}
