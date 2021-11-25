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

import pl.grizzlysoftware.dotykacka.client.v2.model.Supplier;
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
public interface SupplierService {

    /**
     * @param suppliers - suppliers to be created
     * @return Supplier
     */
    @POST(" ")
    Call<Collection<Supplier>> createSuppliers(@Body Collection<Supplier> suppliers);

    /**
     * @param suppliers - suppliers to be created or updated
     * @return Suppliers
     */
    @PUT(" ")
    Call<Collection<Supplier>> updateSuppliers(@Body Collection<Supplier> suppliers);
    
    /**
     * @param supplierId - supplier id to be patched
     * @param supplier - supplier to be updated
     * @return Supplier
     */
    @PUT("{supplierId}")
    Call<Supplier> updateSupplier(@Path("supplierId") Long supplierId, @Body Supplier supplier);

    /**
     * @param supplierId - supplier id to be patched
     * @param supplier - supplier to be patched
     * @return Supplier
     */
    @PATCH("{supplierId}")
    Call<Supplier> patchSupplier(@Path("supplierId") Long supplierId, @Body Supplier supplier);

    /**
     * @param supplierId - supplier id
     * @return Supplier
     */
    @DELETE("{supplierId}")
    Call<Supplier> deleteSupplier(@Path("supplierId") Long supplierId);

    /**
     * @param supplierId - supplierId id
     * @return Supplier
     */
    @GET("{supplierId}")
    Call<Supplier> getSupplier(@Path("supplierId") Long supplierId);

    /**
     * @param page   - pagination parameter, default = 1
     * @param limit  - pagination parameter, default = 100, max = 100
     * @param filter - query filter
     * @param sort   - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return Suppliers
     */
    @GET(" ")
    Call<ResultPage<Supplier>> getSuppliers(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("filter") String filter,
            @Query("sort") String sort
    );
}
