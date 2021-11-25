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

import pl.grizzlysoftware.dotykacka.client.v2.model.ProductCustomization;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.ProductCustomizationService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ProductCustomizationServiceFacade extends DotykackaApiService<ProductCustomizationService> {
    public ProductCustomizationServiceFacade(ProductCustomizationService service) {
        super(service);
    }

    public ProductCustomization createProductCustomization(ProductCustomization productCustomization) {
        return createProductCustomizations(singletonList(productCustomization))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<ProductCustomization> createProductCustomizations(Collection<ProductCustomization> productCustomizations) {
        return execute(service.createProductCustomizations(productCustomizations));
    }

    public Collection<ProductCustomization> updateProductCustomizations(Collection<ProductCustomization> productCustomizations) {
        return execute(service.updateProductCustomizations(productCustomizations));
    }

    public ProductCustomization updateProductCustomization(ProductCustomization productCustomization) {
        return execute(service.updateProductCustomization(productCustomization.id, productCustomization));
    }

    public ProductCustomization patchProductCustomization(ProductCustomization productCustomization) {
        return execute(service.patchProductCustomization(productCustomization.id, productCustomization));
    }

    public ProductCustomization deleteProductCustomization(Long productCustomizationId) {
        return execute(service.deleteProductCustomization(productCustomizationId));
    }

    public ProductCustomization getProductCustomization(Long id) {
        return execute(service.getProductCustomization(id));
    }

    public ResultPage<ProductCustomization> getProductCustomizations(int page, int limit, String filter, String sort) {
        return execute(service.getProductCustomizations(page, limit, filter, sort));
    }

    public ResultPage<ProductCustomization> getProductCustomizations(int page, int limit, String sort) {
        return getProductCustomizations(page, limit, null, sort);
    }

    public Collection<ProductCustomization> getAllProductCustomizations(String sort) {
        return batchLoader.load(page -> getProductCustomizations(page.page, page.pageSize, sort));
    }

    public Collection<ProductCustomization> getAllProductCustomizations() {
        return getAllProductCustomizations(null);
    }
}
