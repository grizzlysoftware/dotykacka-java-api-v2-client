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

import pl.grizzlysoftware.dotykacka.client.v2.model.ProductIngredient;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.ProductIngredientService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ProductIngredientServiceFacade extends DotykackaApiService<ProductIngredientService> {
    public ProductIngredientServiceFacade(ProductIngredientService service) {
        super(service);
    }

    public ProductIngredient createProductIngredient(ProductIngredient productIngredient) {
        return createProductIngredients(singletonList(productIngredient))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<ProductIngredient> createProductIngredients(Collection<ProductIngredient> productIngredients) {
        return execute(service.createProductIngredients(productIngredients));
    }

    public Collection<ProductIngredient> updateProductIngredients(Collection<ProductIngredient> productIngredients) {
        return execute(service.updateProductIngredients(productIngredients));
    }

    public ProductIngredient updateProductIngredient(ProductIngredient productIngredient) {
        return execute(service.updateProductIngredient(productIngredient.id, productIngredient));
    }

    public ProductIngredient patchProductIngredient(ProductIngredient productIngredient) {
        return execute(service.patchProductIngredient(productIngredient.id, productIngredient));
    }

    public ProductIngredient deleteProductIngredient(Long productIngredientId) {
        return execute(service.deleteProductIngredient(productIngredientId));
    }

    public ProductIngredient getProductIngredient(Long id) {
        return execute(service.getProductIngredient(id));
    }

    public ResultPage<ProductIngredient> getProductIngredients(int page, int limit, String filter, String sort) {
        return execute(service.getProductIngredients(page, limit, filter, sort));
    }

    public ResultPage<ProductIngredient> getProductIngredients(int page, int limit, String sort) {
        return getProductIngredients(page, limit, null, sort);
    }

    public Collection<ProductIngredient> getAllProductIngredients(String sort) {
        return batchLoader.load(page -> getProductIngredients(page.page, page.pageSize, sort));
    }

    public Collection<ProductIngredient> getAllProductIngredients() {
        return getAllProductIngredients(null);
    }
}
