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

import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.dotykacka.client.v2.model.Reservation
import pl.grizzlysoftware.dotykacka.client.v2.model.ReservationStatus
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage

import java.time.ZonedDateTime
import java.util.function.Function
import java.util.function.Supplier

class ReservationServiceFacadeTest extends GenericDotykackaServiceFacadeTest<ReservationServiceFacade> {
    private DotykackaApiClient client
    @Override
    Function<DotykackaApiClient, ReservationServiceFacade> serviceFacadeExtractionFunction() {
        return { DotykackaApiClient client ->
            this.client = client
            return client.reservationServiceFacade
        }
    }

    @Override
    CloudEntity getCloudEntity() {
        return reservation()
    }

    @Override
    CrudInvocation getEntityInvocationDefinition() {
        return new CrudInvocation("Reservation", "getById",
                { _void -> service().createReservation(reservation()) },
                { obj -> service().getReservation(obj.id) },
                { obj -> service().deleteReservation(obj.id) }
        )
    }

    @Override
    Supplier<ResultPage<CloudEntity>> getEntitiesPageInvocationDefinition() {
        return { -> service().getReservations(1, 100, null, null)}
    }

    @Override
    CrudInvocation getCreateEntityInvocationDefinition() {
        return new CrudInvocation("Reservation", "create",
                { _void -> reservation() },
                service()::createReservation,
                { obj -> service().deleteReservation(obj.id) }
        )
    }

    @Override
    CrudInvocation getDeleteEntityInvocationDefinition() {
        return new CrudInvocation("Reservation", "delete",
                { _void -> service().createReservation(reservation()) },
                { obj -> service().deleteReservation(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getUpdateEntityInvocationDefinition() {
        return new CrudInvocation("Reservation", "update",
                { _void -> service().createReservation(reservation()) },
                { obj -> service().updateReservation(obj)},
                { obj -> service().deleteReservation(obj.id) }
        )
    }

    @Override
    CrudInvocation getBatchUpdateEntitiesInvocationDefinition() {
        return new CrudInvocation("Reservation", "batch update",
                { _void -> service().getReservations(1, 2, null) },
                { obj ->
                    service().updateReservations(obj.data)
                },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation getPatchEntityInvocationDefinition() {
        return new CrudInvocation("Reservation", "patch",
                { _void -> service().createReservation(reservation()) },
                { obj -> service().patchReservation(obj) },
                { obj -> service().deleteReservation(obj.id) }
        )
    }

    def reservation() {
        def object = new Reservation()
        object.branchId = longVar("BRANCH_ID", 1L)
        object.employeeId = longVar("EMPLOYEE_ID", 1L)
        object.name = "TEST_RESERVATION"
        //non-existing tableId causes endpoint to return 404 status code
        object.tableId = longVar("TABLE_ID", 3161870580511523L)
        object.customerId = longVar("CUSTOMER_ID", 1L)
        object.seatsCount = shortVar("TABLE_SEATS_COUNT", 4)
        object.status = ReservationStatus.NEW
        object.startsAt = ZonedDateTime.now().withNano(0)
        object.endsAt = ZonedDateTime.now().withNano(0).plusHours(5)
        return object
    }
}
