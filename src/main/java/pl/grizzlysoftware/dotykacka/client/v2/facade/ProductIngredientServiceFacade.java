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
