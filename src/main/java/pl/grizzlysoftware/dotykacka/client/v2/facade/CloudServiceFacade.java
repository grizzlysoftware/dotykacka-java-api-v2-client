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
