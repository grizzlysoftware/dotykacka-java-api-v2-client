/*
 * Copyright (c) 2021 Grizzly Software, https://grizzlysoftware.pl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

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
