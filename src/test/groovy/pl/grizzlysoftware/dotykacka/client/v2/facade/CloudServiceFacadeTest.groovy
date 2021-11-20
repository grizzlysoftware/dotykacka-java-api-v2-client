package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.util.ResponseStatusException
import spock.lang.Requires

class CloudServiceFacadeTest extends EnvSpecification {
    CloudServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.cloudServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should not get non-existing entity by id"() {
        given:
            def nonExistingCloudId = 1L
        when:
            def result = service.getCloud(nonExistingCloudId)
        then:
            def e = thrown(ResponseStatusException)
            403 == e.statusCode
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get existing entity by id"() {
        given:
            def existingCloudId = longVar("CLOUD_ID", 344417928L)
        when:
            def result = service.getCloud(existingCloudId)
        then:
            existingCloudId == result.id
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        when:
            def result = service.getClouds(1, 100, null)
        then:
            1 == result.page
            1 <= result.data.size()
    }
}
