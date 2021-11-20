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
