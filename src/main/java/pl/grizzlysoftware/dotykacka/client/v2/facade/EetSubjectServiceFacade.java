package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.EetSubject;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.EetSubjectService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class EetSubjectServiceFacade extends DotykackaApiService<EetSubjectService> {
    public EetSubjectServiceFacade(EetSubjectService service) {
        super(service);
    }

    public EetSubject createEetSubject(EetSubject eetSubject) {
        return createEetSubjects(singletonList(eetSubject))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<EetSubject> createEetSubjects(Collection<EetSubject> eetSubjects) {
        return execute(service.createEetSubjects(eetSubjects));
    }

    public EetSubject updateEetSubject(EetSubject eetSubject) {
        return execute(service.updateEetSubject(eetSubject.id, eetSubject));
    }

    public EetSubject patchEetSubject(EetSubject eetSubject) {
        return execute(service.patchEetSubject(eetSubject.id, eetSubject));
    }

    public EetSubject deleteEetSubject(Long eetSubjectId) {
        return execute(service.deleteEetSubject(eetSubjectId));
    }

    public EetSubject getEetSubject(Long id) {
        return execute(service.getEetSubject(id));
    }

    public ResultPage<EetSubject> getEetSubjects(int page, int pageSize, String filter, String sort) {
        return execute(service.getEetSubjects(page, pageSize, filter, sort));
    }

    public ResultPage<EetSubject> getEetSubjects(int page, int pageSize, String sort) {
        return getEetSubjects(page, pageSize, null, sort);
    }

    public Collection<EetSubject> getAllEetSubjects(String sort) {
        return batchLoader.load(page -> getEetSubjects(page.page, page.pageSize, sort));
    }

    public Collection<EetSubject> getAllEetSubjects() {
        return getAllEetSubjects(null);
    }
}
