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
