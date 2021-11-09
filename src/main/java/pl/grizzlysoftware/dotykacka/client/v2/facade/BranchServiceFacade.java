package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Branch;
import pl.grizzlysoftware.dotykacka.client.v2.service.BranchService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class BranchServiceFacade extends DotykackaApiService<BranchService> {

    public BranchServiceFacade(BranchService service, Long cloudId) {
        super(service, cloudId);
    }

    public Branch getBranch(Long id) {
        return execute(service.getBranch(cloudId, id));
    }

    public Collection<Branch> getBranches(int limit, int offset, String filter, String sort) {
        return execute(service.getBranches(cloudId, limit, offset, filter, sort));
    }

    public Collection<Branch> getBranches(int limit, int offset, String sort) {
        return getBranches(limit, offset, null, sort);
    }

    public Collection<Branch> getBranches(String sort) {
        return batchLoader.load(page -> getBranches(page.limit, page.offset, sort));
    }

    public Collection<Branch> getBranches() {
        return getBranches(null);
    }
}
