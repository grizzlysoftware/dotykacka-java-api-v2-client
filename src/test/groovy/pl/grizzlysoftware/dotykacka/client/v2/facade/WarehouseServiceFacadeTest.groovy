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
