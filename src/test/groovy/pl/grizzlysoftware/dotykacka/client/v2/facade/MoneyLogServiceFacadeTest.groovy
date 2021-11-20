package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.util.ResponseStatusException
import spock.lang.Requires

class MoneyLogServiceFacadeTest extends EnvSpecification {
    MoneyLogServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.moneyLogServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should not get non-existing entity by id"() {
        given:
            def nonExistingMoneyLogId = 1L
        when:
            def result = service.getMoneyLog(nonExistingMoneyLogId)
        then:
            def e = thrown(ResponseStatusException)
            404 == e.statusCode
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get existing entity by id"() {
        given:
            def existingMoneyLogId = longVar("MONEYLOG_ID", 550414500427019L)
        when:
            def result = service.getMoneyLog(existingMoneyLogId)
        then:
            existingMoneyLogId == result.id
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        when:
            def result = service.getMoneyLogs(1, 100, null)
        then:
            1 == result.page
            1 <= result.data.size()
    }
}
