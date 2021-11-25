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
import pl.grizzlysoftware.dotykacka.client.v2.model.Product
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage
import pl.grizzlysoftware.dotykacka.client.v2.model.Unit

import java.util.function.Function
import java.util.function.Supplier

class ProductServiceFacadeTest extends GenericDotykackaServiceFacadeTest<ProductServiceFacade> {

    @Override
    Function<DotykackaApiClient, ProductServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.productServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return product()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Product", "getById",
                { _void -> service().createProduct(product()) },
                { obj -> service().getProduct(obj.id) },
                { obj -> service().deleteProduct(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getProducts(1, 100, null, null) }
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Product", "create",
                { _void -> product() },
                service()::createProduct,
                { obj -> service().deleteProduct(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Product", "delete",
                { _void -> service().createProduct(product()) },
                { obj -> service().deleteProduct(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Product", "update",
                { _void -> service().createProduct(product()) },
                { obj ->
                    service().updateProduct(obj)
                },
                { obj -> service().deleteProduct(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Product", "batch update",
                { _void -> service().getProducts(1, 2, null, null) },
                { obj ->
                    service().updateProducts(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Product", "patch",
                { _void -> service().createProduct(product()) },
                { obj -> service().patchProduct(obj) },
                { obj -> service().deleteProduct(obj.id) }
        )
    }

    def product() {
        def object = new Product()
        object.name = "TEST_PRODUCT"
        object.categoryId = longVar("CATEGORY_ID", 1L)
        object.currency = "PLN"
        object.description = "ABSOLWENT"
        object.eans = [
                "666666666666"
        ]
        object.packageItem = 1
        object.packaging = 1
        object.points = 2.75
        object.priceWithVat = 11.5
        object.priceWithoutVat = 9.34959349593496
        object.purchasePriceWithoutVat = 0
        object.stockOverdraft = "WARN"
        object.unit = Unit.Piece
        object.vat = 1.23
        return object
    }
}
