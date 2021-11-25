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

    public Collection<Supplier> updateSuppliers(Collection<Supplier> suppliers) {
        return execute(service.updateSuppliers(suppliers));
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
