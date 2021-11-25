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
import pl.grizzlysoftware.dotykacka.client.v2.model.ProductCustomization
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class ProductCustomizationServiceFacadeTest extends GenericDotykackaServiceFacadeTest<ProductCustomizationServiceFacade> {

    @Override
    Function<DotykackaApiClient, ProductCustomizationServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.productCustomizationServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return productCustomization()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("ProductCustomization", "getById",
                { _void -> service().createProductCustomization(productCustomization()) },
                { obj -> service().getProductCustomization(obj.id) },
                { obj -> service().deleteProductCustomization(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getProductCustomizations(1, 100, null, null)}
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("ProductCustomization", "create",
                { _void -> productCustomization() },
                service()::createProductCustomization,
                { obj -> service().deleteProductCustomization(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("ProductCustomization", "delete",
                { _void -> service().createProductCustomization(productCustomization()) },
                { obj -> service().deleteProductCustomization(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("ProductCustomization", "update",
                { _void -> service().createProductCustomization(productCustomization()) },
                { obj -> service().updateProductCustomization(obj)},
                { obj -> service().deleteProductCustomization(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("ProductCustomization", "batch update",
                { _void -> service().getProductCustomizations(1, 5, null) },
                { obj ->
                    service().updateProductCustomizations(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("ProductCustomization", "patch",
                { _void -> service().createProductCustomization(productCustomization()) },
                { obj -> service().patchProductCustomization(obj) },
                { obj -> service().deleteProductCustomization(obj.id) }
        )
    }

    def productCustomization() {
        def object = new ProductCustomization()
        object.categoryId = longVar("CATEGORY_ID", 1L)
        object.productId = longVar("PRODUCT_ID", 1L)
        object.defaultProductIds = []
        object.name = "TEST_PRODUCT_CUSTOMIZATION"
        object.minSelected = 1
        object.maxSelected = 1
        object.sortOrder = 1L
        object.priceLevel = null
        object.sortOrder = 2416640
        return object
    }
}
