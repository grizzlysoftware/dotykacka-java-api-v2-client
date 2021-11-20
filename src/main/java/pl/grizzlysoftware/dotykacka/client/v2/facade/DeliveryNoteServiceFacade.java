package pl.grizzlysoftware.dotykacka.client.v2.facade;

import okhttp3.MultipartBody;
import pl.grizzlysoftware.dotykacka.client.v2.model.DeliveryNote;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.DeliveryNoteService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class DeliveryNoteServiceFacade extends DotykackaApiService<DeliveryNoteService> {
    public DeliveryNoteServiceFacade(DeliveryNoteService service) {
        super(service);
    }

    public DeliveryNote createDeliveryNote(DeliveryNote deliveryNote, MultipartBody.Part file) {
        return execute(service.uploadDeliveryNote(deliveryNote, file));
    }

    public DeliveryNote getDeliveryNote(Long id) {
        return execute(service.getDeliveryNote(id));
    }

    public ResultPage<DeliveryNote> getDeliveryNotes(int page, int pageSize, String filter, String sort) {
        return execute(service.getDeliveryNotes(page, pageSize, filter, sort));
    }

    public ResultPage<DeliveryNote> getDeliveryNotes(int page, int pageSize, String sort) {
        return getDeliveryNotes(page, pageSize, null, sort);
    }

    public Collection<DeliveryNote> getAllDeliveryNotes(String sort) {
        return batchLoader.load(page -> getDeliveryNotes(page.page, page.pageSize, sort));
    }

    public Collection<DeliveryNote> getAllDeliveryNotes() {
        return getAllDeliveryNotes(null);
    }
}
