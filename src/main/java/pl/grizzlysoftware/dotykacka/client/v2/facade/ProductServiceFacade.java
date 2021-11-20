package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Product;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.ProductService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ProductServiceFacade extends DotykackaApiService<ProductService> {

    public ProductServiceFacade(ProductService service) {
        super(service);
    }
    
    public Product createProduct(Product product) {
        return createProducts(singletonList(product))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Product> createProducts(Collection<Product> products) {
        return execute(service.createProducts(products));
    }

    public Product updateProduct(Product product) {
        return execute(service.updateProduct(product.id, product));
    }

    public Product patchProduct(Product product) {
        return execute(service.patchProduct(product.id, product));
    }

    public Product deleteProduct(Long productsId) {
        return execute(service.deleteProduct(productsId));
    }
    
    public Product getProduct(Long id) {
        return execute(service.getProduct(id));
    }

    public ResultPage<Product> getProducts(int page, int pageSize, String include, String filter, String sort) {
        return execute(service.getProducts(page, pageSize, include, filter, sort));
    }
    
    public ResultPage<Product> getProducts(int page, int pageSize, String include, String sort) {
        return getProducts(page, pageSize, include, null, sort);
    }

    public Collection<Product> getAllProducts(String include, String filter, String sort) {
        return batchLoader.load(page -> getProducts(page.page, page.pageSize, include, filter, sort));
    }

    public Collection<Product> getAllProductsIncludingIngredients(String filter, String sort) {
        return getAllProducts("ingredients", filter, sort);
    }

    public Collection<Product> getAllProductsIncludingCustomizations(String filter, String sort) {
        return getAllProducts("customizations", filter, sort);
    }

    public Collection<Product> getAllProductsIncludingIngredientsAndCustomizations(String filter, String sort) {
        return getAllProducts("ingredients,customizations", filter, sort);
    }

    public Collection<Product> getAllProducts(String sort) {
        return getAllProducts(null, null, sort);
    }

    public Collection<Product> getAllProductsIncludingIngredients(String sort) {
        return getAllProductsIncludingIngredients(null, sort);
    }

    public Collection<Product> getAllProductsIncludingCustomizations(String sort) {
        return getAllProductsIncludingCustomizations(null, sort);
    }

    public Collection<Product> getAllProductsIncludingIngredientsAndCustomizations(String sort) {
        return getAllProductsIncludingIngredientsAndCustomizations(null, sort);
    }

    public Collection<Product> getAllProducts() {
        return getAllProducts(null);
    }
}