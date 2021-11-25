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
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Category", "getById",
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
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Category", "create",
                { _void -> category() },
                service()::createCategory,
                { obj -> service().deleteCategory(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Category", "delete",
                { _void -> service().createCategory(category()) },
                { obj -> service().deleteCategory(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Category", "update",
                { _void -> service().createCategory(category()) },
                { obj -> service().updateCategory(obj)},
                { obj -> service().deleteCategory(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Category", "batch update",
                { _void -> service().getCategories(1, 5, null) },
                { obj ->
                    obj.data.forEach { it.hexColor = "#000000"}
                    service().updateCategories(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Category", "patch",
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
