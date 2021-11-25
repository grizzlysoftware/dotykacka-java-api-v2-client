package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.facade.DotykackaApiService
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage
import spock.lang.Requires
import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Function
import java.util.function.Supplier

abstract class GenericDotykackaServiceFacadeTest<T extends DotykackaApiService> extends EnvSpecification {
    DotykackaServiceFacadeProvider serviceFacadeProvider
    Function<DotykackaApiClient, T> serviceFacadeExtrationFunction = serviceFacadeExtractionFunction()

    def setup() {
        serviceFacadeProvider = new DotykackaServiceFacadeProvider()
    }

    abstract Function<DotykackaApiClient, T> serviceFacadeExtractionFunction()

    T service() {
        serviceFacadeProvider.getServiceFacade(serviceFacadeExtrationFunction)
    }

    abstract CloudEntity getCloudEntity()

    abstract CrudInvocation getEntityInvocationDefinition()

    abstract Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition()

    abstract CrudInvocation getCreateEntityInvocationDefinition()

    abstract CrudInvocation getDeleteEntityInvocationDefinition()

    abstract CrudInvocation getUpdateEntityInvocationDefinition()

    abstract CrudInvocation getBatchUpdateEntitiesInvocationDefinition()

    abstract CrudInvocation getPatchEntityInvocationDefinition()

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entity by id"() {
        given:
            CrudInvocation inv = getEntityInvocationDefinition()
            CloudEntity object = inv.setupInvocation.apply(null)
        when:
            def result = inv.methodInvocation.apply(object)
        then:
            result.id
        cleanup:
            inv.cleanupInvocation.apply(result)
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        given:
            CrudInvocation createInv = getCreateEntityInvocationDefinition()
            CloudEntity object = createInv.setupInvocation.apply(null)
            def preRequisite = createInv.methodInvocation.apply(object)

            Supplier<ResultPage<CloudEntity>> inv = getEntitiesPageInvocationDefinition()
        when:
            def result = inv.get()
        then:
            1 == result.page
            1 <= result.data.size()
        cleanup:
            createInv.cleanupInvocation.apply(preRequisite)
    }

    @Requires({ env.INTEGRATION_TEST })
    def "create - it should create entity"() {
        given:
            CrudInvocation inv = getCreateEntityInvocationDefinition()
            CloudEntity object = inv.setupInvocation.apply(null)
        when:
            def result = inv.methodInvocation.apply(object)
        then:
            result.id
        cleanup:
            inv.cleanupInvocation.apply(result)
    }

    @Requires({ env.INTEGRATION_TEST })
    def "delete - it should delete entity"() {
        given:
            CrudInvocation inv = getDeleteEntityInvocationDefinition()
            CloudEntity object = inv.setupInvocation.apply(null)
        when:
            def result = inv.methodInvocation.apply(object)
        then:
            result.id
        cleanup:
            inv.cleanupInvocation.apply(null)
    }

    @Requires({ env.INTEGRATION_TEST })
    def "update - it should update entity"() {
        given:
            CrudInvocation inv = getUpdateEntityInvocationDefinition()
            CloudEntity object = inv.setupInvocation.apply(null)
            object.modifiedAt = null
            object.createdAt = null
        when:
            def result = inv.methodInvocation.apply(object)
        then:
            result.id
        cleanup:
            inv.cleanupInvocation.apply(result)
    }

    @Requires({ env.INTEGRATION_TEST })
    def "update - it should batch update entities"() {
        given:
            CrudInvocation inv = getBatchUpdateEntitiesInvocationDefinition()
            ResultPage<CloudEntity> object = inv.setupInvocation.apply(null)
        when:
            def result = inv.methodInvocation.apply(object)
        then:
            result
        cleanup:
            inv.cleanupInvocation.apply(result)
    }

    @Requires({ env.INTEGRATION_TEST })
    def "patch - it should patch entity"() {
        given:
            CrudInvocation inv = getPatchEntityInvocationDefinition()
            CloudEntity object = inv.setupInvocation.apply(null)
            object.modifiedAt = null
            object.createdAt = null
        when:
            def result = inv.methodInvocation.apply(object)
        then:
            result.id
        cleanup:
            inv.cleanupInvocation.apply(result)
    }
}
