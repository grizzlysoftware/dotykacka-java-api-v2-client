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
