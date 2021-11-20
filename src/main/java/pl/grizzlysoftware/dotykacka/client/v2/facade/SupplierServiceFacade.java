package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Supplier;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.SupplierService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class SupplierServiceFacade extends DotykackaApiService<SupplierService> {
    public SupplierServiceFacade(SupplierService service) {
        super(service);
    }

    public Supplier createSupplier(Supplier supplier) {
        return createSuppliers(singletonList(supplier))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Supplier> createSuppliers(Collection<Supplier> suppliers) {
        return execute(service.createSuppliers(suppliers));
    }

    public Supplier updateSupplier(Supplier supplier) {
        return execute(service.updateSupplier(supplier.id, supplier));
    }

    public Supplier patchSupplier(Supplier supplier) {
        return execute(service.patchSupplier(supplier.id, supplier));
    }

    public Supplier deleteSupplier(Long supplierId) {
        return execute(service.deleteSupplier(supplierId));
    }

    public Supplier getSupplier(Long id) {
        return execute(service.getSupplier(id));
    }

    public ResultPage<Supplier> getSuppliers(int page, int pageSize, String filter, String sort) {
        return execute(service.getSuppliers(page, pageSize, filter, sort));
    }

    public ResultPage<Supplier> getSuppliers(int page, int pageSize, String sort) {
        return getSuppliers(page, pageSize, null, sort);
    }

    public Collection<Supplier> getAllSuppliers(String sort) {
        return batchLoader.load(page -> getSuppliers(page.page, page.pageSize, sort));
    }

    public Collection<Supplier> getAllSuppliers() {
        return getAllSuppliers(null);
    }
}
