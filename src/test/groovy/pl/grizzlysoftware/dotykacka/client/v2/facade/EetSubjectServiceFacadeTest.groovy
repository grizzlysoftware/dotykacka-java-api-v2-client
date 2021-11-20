package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.EetSubject
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.util.function.Function
import java.util.function.Supplier

class EetSubjectServiceFacadeTest extends GenericDotykackaServiceFacadeTest<EetSubjectServiceFacade> {

    @Override
    Function<DotykackaApiClient, EetSubjectServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client -> client.eetSubjectServiceFacade }
    }

    @Override
    CloudEntity getCloudEntity() {
        return eetSubject()
    }

    @Override
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("EetSubject", "getById",
                { _void -> service().createEetSubject(eetSubject()) },
                { obj -> service().getEetSubject(obj.id) },
                { obj -> service().deleteEetSubject(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getEetSubjects(1, 100, null, null)}
    }

    @Override
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("EetSubject", "create",
                { _void -> eetSubject() },
                service()::createEetSubject,
                { obj -> service().deleteEetSubject(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("EetSubject", "delete",
                { _void -> service().createEetSubject(eetSubject()) },
                { obj -> service().deleteEetSubject(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("EetSubject", "update",
                { _void -> service().createEetSubject(eetSubject()) },
                { obj -> service().updateEetSubject(obj)},
                { obj -> service().deleteEetSubject(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("EetSubject", "patch",
                { _void -> service().createEetSubject(eetSubject()) },
                { obj -> service().patchEetSubject(obj) },
                { obj -> service().deleteEetSubject(obj.id) }
        )
    }

    static def eetSubject() {
        def object = new EetSubject()
        object.name = "TEST_EET_SUBJECT"
        return object
    }
}
