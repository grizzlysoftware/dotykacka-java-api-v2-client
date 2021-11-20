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
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("DiscountGroup", "getById",
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
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("DiscountGroup", "create",
                { _void -> discountGroup() },
                service()::createDiscountGroup,
                { obj -> service().deleteDiscountGroup(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("DiscountGroup", "delete",
                { _void -> service().createDiscountGroup(discountGroup()) },
                { obj -> service().deleteDiscountGroup(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("DiscountGroup", "update",
                { _void -> service().createDiscountGroup(discountGroup()) },
                { obj -> service().updateDiscountGroup(obj)},
                { obj -> service().deleteDiscountGroup(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("DiscountGroup", "patch",
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
