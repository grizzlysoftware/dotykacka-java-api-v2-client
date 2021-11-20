package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.util.ResponseStatusException
import spock.lang.Requires

class TableServiceFacadeTest extends EnvSpecification {
    TableServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.tableServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should not get non-existing entity by id"() {
        given:
            def nonExistingTableId = 1L
        when:
            def result = service.getTable(nonExistingTableId)
        then:
            def e = thrown(ResponseStatusException)
            404 == e.statusCode
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get existing entity by id"() {
        given:
            def existingTableId = longVar("TABLE_ID", 3161870580511523L)
        when:
            def result = service.getTable(existingTableId)
        then:
            existingTableId == result.id
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        when:
            def result = service.getTables(1, 100, null)
        then:
            1 == result.page
            1 <= result.data.size()
    }
}
