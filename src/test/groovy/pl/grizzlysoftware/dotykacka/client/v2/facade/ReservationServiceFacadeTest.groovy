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
    CrudInvocation<CloudEntity> getEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Reservation", "getById",
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
    CrudInvocation<CloudEntity> getCreateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Reservation", "create",
                { _void -> reservation() },
                service()::createReservation,
                { obj -> service().deleteReservation(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getDeleteEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Reservation", "delete",
                { _void -> service().createReservation(reservation()) },
                { obj -> service().deleteReservation(obj.id) },
                { obj -> null }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getUpdateEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Reservation", "update",
                { _void -> service().createReservation(reservation()) },
                { obj -> service().updateReservation(obj)},
                { obj -> service().deleteReservation(obj.id) }
        )
    }

    @Override
    CrudInvocation<CloudEntity> getPatchEntityInvocationDefinition() {
        return new CrudInvocation<CloudEntity>("Reservation", "patch",
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
