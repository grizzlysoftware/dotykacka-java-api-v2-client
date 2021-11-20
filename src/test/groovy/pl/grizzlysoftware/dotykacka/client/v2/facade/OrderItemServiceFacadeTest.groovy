package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.util.ResponseStatusException
import spock.lang.Requires

import java.time.ZonedDateTime

class OrderItemServiceFacadeTest extends EnvSpecification {
    OrderItemServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.orderItemServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should not get non-existing entity by id"() {
        given:
            def nonExistingOrderItemId = 1L
        when:
            def result = service.getOrderItem(nonExistingOrderItemId)
        then:
            def e = thrown(ResponseStatusException)
            404 == e.statusCode
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get existing entity by id"() {
        given:
            def existingOrderItemId = longVar("ORDER_ITEM_ID", 811196324705935L)
        when:
            def result = service.getOrderItem(existingOrderItemId)
        then:
            existingOrderItemId == result.id
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit"() {
        when:
            def result = service.getOrderItems(1, 100, null, null)
        then:
            1 == result.page
            1 <= result.data.size()
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should get entities by page and limit for given timerange"() {
        when:
            def base = ZonedDateTime.now()
            def result = service.getOrderItemsForTimeRange(base.minusDays(2), base.minusDays(1), 1, 2, null)
        then:
            1 == result.page
            2 == result.data.size()
    }
}
