package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.util.ResponseStatusException
import spock.lang.Requires

class TagServiceFacadeTest extends EnvSpecification {
    TagServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.tagServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should not get non-existing entity by id"() {
        given:
            def nonExistingTagId = 1L
        when:
            def result = service.getTag(nonExistingTagId)
        then:
            def e = thrown(ResponseStatusException)
            404 == e.statusCode
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get existing entity by id"() {
        given:
            def existingTagId = longVar("TAG_ID", 47041054L)
        when:
            def result = service.getTag(existingTagId)
        then:
            existingTagId == result.id
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        when:
            def result = service.getTags(1, 100, null)
        then:
            1 == result.page
            1 <= result.data.size()
    }
}
