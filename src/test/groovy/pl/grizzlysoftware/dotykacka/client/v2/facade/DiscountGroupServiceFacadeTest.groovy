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
import pl.grizzlysoftware.dotykacka.client.v2.model.DiscountGroup
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class DiscountGroupServiceFacadeTest extends GenericDotykackaServiceFacadeTest<DiscountGroupServiceFacade> {

    @Override
    Function<DotykackaApiClient, DiscountGroupServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.discountGroupServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return discountGroup()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("DiscountGroup", "getById",
                { _void -> service().createDiscountGroup(discountGroup()) },
                { obj -> service().getDiscountGroup(obj.id) },
                { obj -> service().deleteDiscountGroup(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getDiscountGroups(1, 100, null)}
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("DiscountGroup", "create",
                { _void -> discountGroup() },
                service()::createDiscountGroup,
                { obj -> service().deleteDiscountGroup(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("DiscountGroup", "delete",
                { _void -> service().createDiscountGroup(discountGroup()) },
                { obj -> service().deleteDiscountGroup(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("DiscountGroup", "update",
                { _void -> service().createDiscountGroup(discountGroup()) },
                { obj -> service().updateDiscountGroup(obj)},
                { obj -> service().deleteDiscountGroup(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("DiscountGroup", "batch update",
                { _void -> service().getDiscountGroups(1, 5, null) },
                { obj ->
                    service().updateDiscountGroups(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("DiscountGroup", "patch",
                { _void -> service().createDiscountGroup(discountGroup()) },
                { obj -> service().patchDiscountGroup(obj) },
                { obj -> service().deleteDiscountGroup(obj.id) }
        )
    }

    static def discountGroup() {
        def object = new DiscountGroup()
        object.discountPercent = 0.05
        object.name = "TEST_DISCOUNT_GROUP"
        return object
    }
}
