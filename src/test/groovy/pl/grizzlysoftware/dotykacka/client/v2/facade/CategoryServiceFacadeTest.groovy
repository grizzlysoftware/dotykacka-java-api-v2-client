package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.Category
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class CategoryServiceFacadeTest extends GenericDotykackaServiceFacadeTest<CategoryServiceFacade> {

    @Override
    Function<DotykackaApiClient, CategoryServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.categoryServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return category()
    }

    @Override
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Category", "getById",
                { _void -> service().createCategory(category()) },
                { obj -> service().getCategory(obj.id) },
                { obj -> service().deleteCategory(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getCategories(1, 100, null)}
    }

    @Override
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Category", "create",
                { _void -> category() },
                service()::createCategory,
                { obj -> service().deleteCategory(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Category", "delete",
                { _void -> service().createCategory(category()) },
                { obj -> service().deleteCategory(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Category", "update",
                { _void -> service().createCategory(category()) },
                { obj -> service().updateCategory(obj)},
                { obj -> service().deleteCategory(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Category", "patch",
                { _void -> service().createCategory(category()) },
                { obj -> service().patchCategory(obj) },
                { obj -> service().deleteCategory(obj.id) }
        )
    }

    static def category() {
        def object = new Category()
        object.name = "TEST_CATEGORY"
        object.margin = ""
        object.maxDiscount = 0.0
        object.vat = 1.23
        return object
    }
}
