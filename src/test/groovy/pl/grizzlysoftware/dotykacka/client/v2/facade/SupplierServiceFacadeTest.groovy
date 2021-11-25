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
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Supplier", "getById",
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
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Supplier", "create",
                { _void -> supplier() },
                service()::createSupplier,
                { obj -> service().deleteSupplier(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Supplier", "delete",
                { _void -> service().createSupplier(supplier()) },
                { obj -> service().deleteSupplier(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Supplier", "update",
                { _void -> service().createSupplier(supplier()) },
                { obj -> service().updateSupplier(obj)},
                { obj -> service().deleteSupplier(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Supplier", "batch update",
                { _void -> service().getSuppliers(1, 2, null) },
                { obj ->
                    service().updateSuppliers(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Supplier", "patch",
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
