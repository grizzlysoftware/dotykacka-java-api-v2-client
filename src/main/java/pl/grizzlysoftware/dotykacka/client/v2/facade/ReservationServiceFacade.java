package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Reservation;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.ReservationService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ReservationServiceFacade extends DotykackaApiService<ReservationService> {
    public ReservationServiceFacade(ReservationService service) {
        super(service);
    }

    public Reservation createReservation(Reservation reservation) {
        return createReservations(singletonList(reservation))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Reservation> createReservations(Collection<Reservation> reservations) {
        return execute(service.createReservations(reservations));
    }

    public Collection<Reservation> updateReservations(Collection<Reservation> reservations) {
        return execute(service.updateReservations(reservations));
    }

    public Reservation updateReservation(Reservation reservation) {
        return execute(service.updateReservation(reservation.id, reservation));
    }

    public Reservation patchReservation(Reservation reservation) {
        return execute(service.patchReservation(reservation.id, reservation));
    }

    public Reservation deleteReservation(Long reservationId) {
        return execute(service.deleteReservation(reservationId));
    }

    public Reservation getReservation(Long id) {
        return execute(service.getReservation(id));
    }

    public ResultPage<Reservation> getReservations(int page, int pageSize, String filter, String sort) {
        return execute(service.getReservations(page, pageSize, filter, sort));
    }

    public ResultPage<Reservation> getReservations(int page, int pageSize, String sort) {
        return getReservations(page, pageSize, null, sort);
    }

    public Collection<Reservation> getAllReservations(String sort) {
        return batchLoader.load(page -> getReservations(page.page, page.pageSize, sort));
    }

    public Collection<Reservation> getAllReservations() {
        return getAllReservations(null);
    }
}
