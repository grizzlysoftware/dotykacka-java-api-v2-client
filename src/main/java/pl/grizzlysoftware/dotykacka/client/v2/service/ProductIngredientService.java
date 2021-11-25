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

import pl.grizzlysoftware.dotykacka.client.v2.model.ProductIngredient;
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
public interface ProductIngredientService {

    /**
     * @param productIngredients - productIngredients to be created
     * @return ProductIngredients
     */
    @POST(" ")
    Call<Collection<ProductIngredient>> createProductIngredients(@Body Collection<ProductIngredient> productIngredients);

    /**
     * @param productIngredients - productIngredients to be created or updated
     * @return ProductIngredients
     */
    @PUT(" ")
    Call<Collection<ProductIngredient>> updateProductIngredients(@Body Collection<ProductIngredient> productIngredients);

    /**
     * @param productIngredientId - id of productIngredient to be updated
     * @param productIngredient   - productIngredient to be updated
     * @return ProductIngredient
     */
    @PUT("{productIngredientId}")
    Call<ProductIngredient> updateProductIngredient(@Path("productIngredientId") Long productIngredientId, @Body ProductIngredient productIngredient);

    /**
     * @param productIngredientId - id of productIngredient to be patched
     * @param productIngredient   - productIngredient to be patched
     * @return ProductIngredient
     */
    @PATCH("{productIngredientId}")
    Call<ProductIngredient> patchProductIngredient(@Path("productIngredientId") Long productIngredientId, @Body ProductIngredient productIngredient);

    /**
     * @param productIngredientId - productIngredient id
     * @return ProductIngredient
     */
    @DELETE("{productIngredientId}")
    Call<ProductIngredient> deleteProductIngredient(@Path("productIngredientId") Long productIngredientId);

    /**
     * @param productIngredientId - productIngredientId id
     * @return ProductIngredient
     */
    @GET("{productIngredientId}")
    Call<ProductIngredient> getProductIngredient(@Path("productIngredientId") Long productIngredientId);

    /**
     * @param page   - pagination parameter, default = 1
     * @param limit  - pagination parameter, default = 100, max = 100
     * @param filter - query filter
     * @param sort   - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return ProductIngredients
     */
    @GET(" ")
    Call<ResultPage<ProductIngredient>> getProductIngredients(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("filter") String filter,
            @Query("sort") String sort
    );
}
