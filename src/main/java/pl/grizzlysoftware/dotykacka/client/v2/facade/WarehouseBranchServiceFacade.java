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
