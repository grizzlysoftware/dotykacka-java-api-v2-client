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

package pl.grizzlysoftware.dotykacka.client.v2.service;

import pl.grizzlysoftware.dotykacka.client.v2.model.Product;
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
public interface ProductService {

    /**
     * @param products - products to be created
     * @return Products
     */
    @POST(" ")
    Call<Collection<Product>> createProducts(@Body Collection<Product> products);

    /**
     * @param products - products to be created or updated
     * @return Products
     */
    @PUT(" ")
    Call<Collection<Product>> updateProducts(@Body Collection<Product> products);

    /**
     * @param productId - id of product to be updated
     * @param product   - product to be updated
     * @return Product
     */
    @PUT("{productId}")
    Call<Product> updateProduct(@Path("productId") Long productId, @Body Product product);

    /**
     * @param productId - id of product to be patched
     * @param product   - product to be patched
     * @return Product
     */
    @PATCH("{productId}")
    Call<Product> patchProduct(@Path("productId") Long productId, @Body Product product);

    /**
     * @param productId - product id
     * @return Product
     */
    @DELETE("{productId}")
    Call<Product> deleteProduct(@Path("productId") Long productId);

    /**
     * @param productId - productId id
     * @return Product
     */
    @GET("{productId}")
    Call<Product> getProduct(@Path("productId") Long productId);

    /**
     * @param page    - pagination parameter, default = 1
     * @param include - includes specified related entities i.e. moneyLogs or/and orderItems: "include=customizations,ingredients"
     * @param limit   - pagination parameter, default = 100, max = 100
     * @param filter  - query filter
     * @param sort    - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return Products
     */
    @GET(" ")
    Call<ResultPage<Product>> getProducts(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("include") String include,
            @Query("filter") String filter,
            @Query("sort") String sort
    );
}
