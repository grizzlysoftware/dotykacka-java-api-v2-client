package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Tag;
import pl.grizzlysoftware.dotykacka.client.v2.model.Tag;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.TagService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class TagServiceFacade extends DotykackaApiService<TagService> {

    public TagServiceFacade(TagService service) {
        super(service);
    }

    public Tag getTag(Long id) {
        return execute(service.getTag(id));
    }
    
    public Tag createTag(Tag tag) {
        return createTags(singletonList(tag))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Tag> createTags(Collection<Tag> tags) {
        return execute(service.createTags(tags));
    }

    public ResultPage<Tag> getTags(int page, int pageSize, String filter, String sort) {
        return execute(service.getTags(page, pageSize, filter, sort));
    }

    public ResultPage<Tag> getTags(int page, int pageSize, String sort) {
        return getTags(page, pageSize, null, sort);
    }

    public Collection<Tag> getAllTags(String sort) {
        return batchLoader.load(page -> getTags(page.page, page.pageSize, sort));
    }

    public Collection<Tag> getAllTags() {
        return getAllTags(null);
    }
}
