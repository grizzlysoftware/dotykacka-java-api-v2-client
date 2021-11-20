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
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("ProductIngredient", "getById",
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
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("ProductIngredient", "create",
                { _void -> productIngredient() },
                service()::createProductIngredient,
                { obj -> service().deleteProductIngredient(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("ProductIngredient", "delete",
                { _void -> service().createProductIngredient(productIngredient()) },
                { obj -> service().deleteProductIngredient(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("ProductIngredient", "update",
                { _void -> service().createProductIngredient(productIngredient()) },
                { obj -> service().updateProductIngredient(obj)},
                { obj -> service().deleteProductIngredient(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("ProductIngredient", "patch",
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
