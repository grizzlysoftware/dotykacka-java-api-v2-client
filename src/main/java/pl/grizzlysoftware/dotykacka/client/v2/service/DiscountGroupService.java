/*
 * Copyright 2019 Grizzly Software, https://grizzlysoftware.pl
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
 */

package pl.grizzlysoftware.dotykacka.client.v2.service;

import pl.grizzlysoftware.dotykacka.client.v2.model.DiscountGroup;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface DiscountGroupService {

    /**
     * @param discountGroups - discountGroups to be created
     * @return DiscountGroups
     */
    @POST(" ")
    Call<Collection<DiscountGroup>> createDiscountGroups(@Body Collection<DiscountGroup> discountGroups);

    /**
     * @param discountGroups - discountGroups to be created or updated
     * @return DiscountGroups
     */
    @PUT(" ")
    Call<Collection<DiscountGroup>> updateDiscountGroups(@Body Collection<DiscountGroup> discountGroups);

    /**
     * @param discountGroupId - id of discountGroup to be updated
     * @param discountGroup   - discountGroup to be updated
     * @return DiscountGroup
     */
    @PUT("{discountGroupId}")
    Call<DiscountGroup> updateDiscountGroup(@Path("discountGroupId") Long discountGroupId, @Body DiscountGroup discountGroup);

    /**
     * @param discountGroupId - id of discountGroup to be patched
     * @param discountGroup   - discountGroup to be patched
     * @return DiscountGroup
     */
    @PATCH("{discountGroupId}")
    Call<DiscountGroup> patchDiscountGroup(@Path("discountGroupId") Long discountGroupId, @Body DiscountGroup discountGroup);

    /**
     * @param discountGroupId - discountGroup id
     * @return DiscountGroup
     */
    @DELETE("{discountGroupId}")
    Call<DiscountGroup> deleteDiscountGroup(@Path("discountGroupId") Long discountGroupId);

    /**
     * @param discountGroupId - discountGroupId id
     * @return DiscountGroup
     */
    @GET("{discountGroupId}")
    Call<DiscountGroup> getDiscountGroup(@Path("discountGroupId") Long discountGroupId);

    /**
     * @param page   - pagination parameter, default = 1
     * @param limit  - pagination parameter, default = 100, max = 100
     * @param filter - query filter
     * @param sort   - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return Categories
     */
    @GET(" ")
    Call<ResultPage<DiscountGroup>> getDiscountGroups(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("filter") String filter,
            @Query("sort") String sort
    );

}
