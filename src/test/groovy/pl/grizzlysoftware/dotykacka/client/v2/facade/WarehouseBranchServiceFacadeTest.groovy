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
