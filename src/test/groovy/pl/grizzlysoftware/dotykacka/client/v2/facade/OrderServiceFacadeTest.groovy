package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.util.ResponseStatusException
import spock.lang.Requires

class OrderServiceFacadeTest extends EnvSpecification {
    OrderServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.orderServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should not get non-existing entity by id"() {
        given:
            def nonExistingOrderId = 1L
        when:
            def result = service.getOrder(nonExistingOrderId)
        then:
            def e = thrown(ResponseStatusException)
            404 == e.statusCode
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get existing entity by id"() {
        given:
            def existingOrderId = longVar("ORDER_ID", 3365872938756790L)
        when:
            def result = service.getOrder(existingOrderId)
        then:
            existingOrderId == result.id
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        when:
            def result = service.getOrders(1, 100, null, null)
        then:
            1 == result.page
            1 <= result.data.size()
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit including money log"() {
        when:
            def result = service.getOrdersIncludingMoneyLogs(1, 2, null, null)
        then:
            1 == result.page
            1 <= result.data.size()
            1 <= result.data[0].moneyLogs.size()
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit including order items"() {
        when:
            def result = service.getOrdersIncludingOrderItems(1, 2, null, null)
        then:
            1 == result.page
            1 <= result.data.size()
            1 <= result.data[0].orderItems.size()
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit including money logs and order items"() {
        when:
            def result = service.getOrdersIncludingMoneyLogsAndOrderItems(1, 2, null, null)
        then:
            1 == result.page
            1 <= result.data.size()
            1 <= result.data[0].moneyLogs.size()
            1 <= result.data[0].orderItems.size()
    }
}
