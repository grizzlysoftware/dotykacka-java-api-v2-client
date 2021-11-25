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
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("EetSubject", "getById",
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
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("EetSubject", "create",
                { _void -> eetSubject() },
                service()::createEetSubject,
                { obj -> service().deleteEetSubject(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("EetSubject", "delete",
                { _void -> service().createEetSubject(eetSubject()) },
                { obj -> service().deleteEetSubject(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("EetSubject", "update",
                { _void -> service().createEetSubject(eetSubject()) },
                { obj -> service().updateEetSubject(obj)},
                { obj -> service().deleteEetSubject(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("EetSubject", "batch update",
                { _void -> service().getEetSubjects(1, 5, null) },
                { obj ->
                    service().updateEetSubject(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("EetSubject", "patch",
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
