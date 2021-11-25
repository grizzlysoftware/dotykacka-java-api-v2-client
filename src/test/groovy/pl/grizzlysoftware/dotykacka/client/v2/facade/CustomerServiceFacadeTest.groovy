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
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Customer", "getById",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().getCustomer(obj.id) },
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getCustomers(1, 100, null) }
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Customer", "create",
                { _void -> customer() },
                service()::createCustomer,
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Customer", "delete",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().deleteCustomer(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Customer", "update",
                { _void -> service().createCustomer(customer()) },
                { obj -> service().updateCustomer(obj) },
                { obj -> service().deleteCustomer(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Customer", "batch update",
                { _void -> service().getCustomers(1, 5, null) },
                { obj ->
                    obj.data.forEach {
                        if (it.tags == null) {
                            it.tags = []
                        }
                    }
                    service().updateCustomers(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Customer", "patch",
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
