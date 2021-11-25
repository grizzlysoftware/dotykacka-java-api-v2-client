package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseBranch;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.WarehouseBranchService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class WarehouseBranchServiceFacade extends DotykackaApiService<WarehouseBranchService> {
    public WarehouseBranchServiceFacade(WarehouseBranchService service) {
        super(service);
    }

    public WarehouseBranch createWarehouseBranch(WarehouseBranch warehouseBranch) {
        return createWarehouseBranches(singletonList(warehouseBranch))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<WarehouseBranch> createWarehouseBranches(Collection<WarehouseBranch> warehouseBranches) {
        return execute(service.createWarehouseBranches(warehouseBranches));
    }

    public Collection<WarehouseBranch> updateWarehouseBranches(Collection<WarehouseBranch> warehouseBranches) {
        return execute(service.updateWarehouseBranches(warehouseBranches));
    }

    public WarehouseBranch updateWarehouseBranch(WarehouseBranch warehouseBranch) {
        return execute(service.updateWarehouseBranch(warehouseBranch.id, warehouseBranch));
    }

    public WarehouseBranch patchWarehouseBranch(WarehouseBranch warehouseBranch) {
        return execute(service.patchWarehouseBranch(warehouseBranch.id, warehouseBranch));
    }

    public WarehouseBranch getWarehouseBranch(Long id) {
        return execute(service.getWarehouseBranch(id));
    }

    public ResultPage<WarehouseBranch> getWarehouseBranches(int page, int pageSize, String filter, String sort) {
        return execute(service.getWarehouseBranches(page, pageSize, filter, sort));
    }

    public ResultPage<WarehouseBranch> getWarehouseBranches(int page, int pageSize, String sort) {
        return getWarehouseBranches(page, pageSize, null, sort);
    }

    public Collection<WarehouseBranch> getAllWarehouseBranches(String sort) {
        return batchLoader.load(page -> getWarehouseBranches(page.page, page.pageSize, sort));
    }

    public Collection<WarehouseBranch> getAllWarehouseBranches() {
        return getAllWarehouseBranches(null);
    }
}
