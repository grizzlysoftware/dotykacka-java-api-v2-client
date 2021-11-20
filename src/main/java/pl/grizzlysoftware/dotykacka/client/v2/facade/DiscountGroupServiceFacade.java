package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.DiscountGroup;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.DiscountGroupService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class DiscountGroupServiceFacade extends DotykackaApiService<DiscountGroupService> {
    public DiscountGroupServiceFacade(DiscountGroupService service) {
        super(service);
    }

    public DiscountGroup createDiscountGroup(DiscountGroup category) {
        return createDiscountGroups(singletonList(category))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<DiscountGroup> createDiscountGroups(Collection<DiscountGroup> categories) {
        return execute(service.createDiscountGroups(categories));
    }

    public DiscountGroup updateDiscountGroup(DiscountGroup category) {
        return execute(service.updateDiscountGroup(category.id, category));
    }

    public DiscountGroup patchDiscountGroup(DiscountGroup category) {
        return execute(service.patchDiscountGroup(category.id, category));
    }

    public DiscountGroup deleteDiscountGroup(Long categoryId) {
        return execute(service.deleteDiscountGroup(categoryId));
    }

    public DiscountGroup getDiscountGroup(Long id) {
        return execute(service.getDiscountGroup(id));
    }

    public ResultPage<DiscountGroup> getDiscountGroups(int page, int pageSize, String filter, String sort) {
        return execute(service.getDiscountGroups(page, pageSize, filter, sort));
    }

    public ResultPage<DiscountGroup> getDiscountGroups(int page, int pageSize, String sort) {
        return getDiscountGroups(page, pageSize, null, sort);
    }

    public Collection<DiscountGroup> getAllDiscountGroups(String sort) {
        return batchLoader.load(page -> getDiscountGroups(page.page, page.pageSize, sort));
    }

    public Collection<DiscountGroup> getAllDiscountGroups() {
        return getAllDiscountGroups(null);
    }
}
