package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Category;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.CategoryService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CategoryServiceFacade extends DotykackaApiService<CategoryService> {
    public CategoryServiceFacade(CategoryService service) {
        super(service);
    }

    public Category createCategory(Category category) {
        return createCategories(singletonList(category))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Category> createCategories(Collection<Category> categories) {
        return execute(service.createCategories(categories));
    }

    public Category updateCategory(Category category) {
        return execute(service.updateCategory(category.id, category));
    }

    public Category patchCategory(Category category) {
        return execute(service.patchCategory(category.id, category));
    }

    public Category deleteCategory(Long categoryId) {
        return execute(service.deleteCategory(categoryId));
    }

    public Category getCategory(Long id) {
        return execute(service.getCategory(id));
    }

    public ResultPage<Category> getCategories(int page, int pageSize, String filter, String sort) {
        return execute(service.getCategories(page, pageSize, filter, sort));
    }

    public ResultPage<Category> getCategories(int page, int pageSize, String sort) {
        return getCategories(page, pageSize, null, sort);
    }

    public Collection<Category> getAllCategories(String sort) {
        return batchLoader.load(page -> getCategories(page.page, page.pageSize, sort));
    }

    public Collection<Category> getAllCategories() {
        return getAllCategories(null);
    }
}
