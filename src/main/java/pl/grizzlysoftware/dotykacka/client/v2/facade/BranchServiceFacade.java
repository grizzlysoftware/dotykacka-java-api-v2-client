package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Branch;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.BranchService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class BranchServiceFacade extends DotykackaApiService<BranchService> {

    public BranchServiceFacade(BranchService service) {
        super(service);
    }

    public Branch getBranch(Long id) {
        return execute(service.getBranch(id));
    }

    public ResultPage<Branch> getBranches(int page, int pageSize, String filter, String sort) {
        return execute(service.getBranches(page, pageSize, filter, sort));
    }

    public ResultPage<Branch> getBranches(int page, int pageSize, String sort) {
        return getBranches(page, pageSize, null, sort);
    }

    public Collection<Branch> getAllBranches(String sort) {
        return batchLoader.load(page -> getBranches(page.page, page.pageSize, sort));
    }

    public Collection<Branch> getAllBranches() {
        return getAllBranches(null);
    }
}
