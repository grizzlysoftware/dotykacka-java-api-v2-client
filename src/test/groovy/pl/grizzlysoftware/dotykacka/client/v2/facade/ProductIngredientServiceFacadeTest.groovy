package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.ProductIngredient
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage
import pl.grizzlysoftware.dotykacka.client.v2.model.Unit

import java.util.function.Function
import java.util.function.Supplier

class ProductIngredientServiceFacadeTest extends GenericDotykackaServiceFacadeTest<ProductIngredientServiceFacade> {

    @Override
    Function<DotykackaApiClient, ProductIngredientServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.productIngredientServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return productIngredient()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("ProductIngredient", "getById",
                { _void -> service().createProductIngredient(productIngredient()) },
                { obj -> service().getProductIngredient(obj.id) },
                { obj -> service().deleteProductIngredient(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getProductIngredients(1, 100, null, null)}
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("ProductIngredient", "create",
                { _void -> productIngredient() },
                service()::createProductIngredient,
                { obj -> service().deleteProductIngredient(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("ProductIngredient", "delete",
                { _void -> service().createProductIngredient(productIngredient()) },
                { obj -> service().deleteProductIngredient(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("ProductIngredient", "update",
                { _void -> service().createProductIngredient(productIngredient()) },
                { obj -> service().updateProductIngredient(obj)},
                { obj -> service().deleteProductIngredient(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("ProductIngredient", "batch update",
                { _void -> service().getProductIngredients(1, 5, null) },
                { obj ->
                    service().updateProductIngredient(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("ProductIngredient", "patch",
                { _void -> service().createProductIngredient(productIngredient()) },
                { obj -> service().patchProductIngredient(obj) },
                { obj -> service().deleteProductIngredient(obj.id) }
        )
    }

    def productIngredient() {
        def object = new ProductIngredient()
        object.ingredientId = longVar("INGREDIENT_ID", 1L)
        object.productId = longVar("PRODUCT_ID", 2L)
        object.quantity = 1.0
        object.unit = Unit.Piece
        return object
    }
}
