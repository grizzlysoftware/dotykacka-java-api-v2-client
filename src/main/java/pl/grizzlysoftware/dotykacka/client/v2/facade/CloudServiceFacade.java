package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Cloud;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.CloudService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CloudServiceFacade extends DotykackaApiService<CloudService> {

    public CloudServiceFacade(CloudService service) {
        super(service);
    }

    public Cloud getCloud(Long id) {
        return execute(service.getCloud(id));
    }

    public ResultPage<Cloud> getClouds(int page, int pageSize, String filter, String sort) {
        return execute(service.getClouds(page, pageSize, filter, sort));
    }

    public ResultPage<Cloud> getClouds(int page, int pageSize, String sort) {
        return getClouds(page, pageSize, null, sort);
    }

    public Collection<Cloud> getAllClouds(String sort) {
        return batchLoader.load(page -> getClouds(page.page, page.pageSize, sort));
    }

    public Collection<Cloud> getAllClouds() {
        return getAllClouds(null);
    }
}
