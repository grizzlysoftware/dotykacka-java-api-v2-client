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
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("WarehouseBranch", "getById",
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
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("WarehouseBranch", "create",
                { _void -> warehouseBranch() },
                service()::createWarehouseBranch,
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("WarehouseBranch", "delete",
                { _void -> service().createWarehouseBranch(warehouseBranch()) },
                { obj -> null },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("WarehouseBranch", "update",
                { _void -> service().createWarehouseBranch(warehouseBranch()) },
                { obj -> service().updateWarehouseBranch(obj)},
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("WarehouseBranch", "batch update",
                { _void -> service().getWarehouseBranches(1, 2, null) },
                { obj ->
                    service().updateWarehouseBranches(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("WarehouseBranch", "patch",
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
