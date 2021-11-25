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
