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

import com.fasterxml.jackson.databind.JsonNode;
import pl.grizzlysoftware.dotykacka.client.v2.model.ProductStock;
import pl.grizzlysoftware.dotykacka.client.v2.model.Warehouse;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseBranch;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseItemSale;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseStockUp;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseTransfer;
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
public interface WarehouseService {

    /**
     * @param warehouses - warehouses to be created
     * @return Warehouses
     */
    @POST(" ")
    Call<Collection<Warehouse>> createWarehouses(@Body Collection<Warehouse> warehouses);
    
    /**
     * @param warehouses - warehouses to be created or updated
     * @return Warehouses
     */
    @PUT(" ")
    Call<Collection<Warehouse>> updateWarehouses(@Body Collection<Warehouse> warehouses);
    
    /**
     * @param warehouseId - warehouse id
     * @param warehouse   - warehouse to be updated
     * @return Warehouse
     */
    @PUT("{warehouseId}")
    Call<Warehouse> updateWarehouse(@Path("warehouseId") Long warehouseId, @Body Warehouse warehouse);

    /**
     * @param warehouseId - warehouse id
     * @param warehouse   - warehouse to be patched
     * @return Warehouse
     */
    @PATCH("{warehouseId}")
    Call<Warehouse> patchWarehouse(@Path("warehouseId") Long warehouseId, @Body Warehouse warehouse);

    /**
     * @param warehouseId - warehouse id
     * @return Warehouse
     */
    @DELETE("{warehouseId}")
    Call<Warehouse> deleteWarehouse(@Path("warehouseId") Long warehouseId);

    /**
     * @param warehouseId - warehouseId id
     * @return Warehouse
     */
    @GET("{warehouseId}")
    Call<Warehouse> getWarehouse(@Path("warehouseId") Long warehouseId);

    /**
     * @param page   - pagination parameter, default = 1
     * @param limit  - pagination parameter, default = 100, max = 100
     * @param filter - query filter
     * @param sort   - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return Warehouses
     */
    @GET(" ")
    Call<ResultPage<Warehouse>> getWarehouses(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("filter") String filter,
            @Query("sort") String sort
    );

    /**
     * @param warehouseId - warehouse from which product stocks should be fetched
     * @return Product stock
     */
    @GET("{warehouseId}/products")
    Call<Collection<ProductStock>> getProductStocks(@Path("warehouseId") Long warehouseId);

    /**
     * @param warehouseId - warehouse from which product stock should be fetched
     * @param productId   - id of product for which stock should be fetched
     * @return Product stock
     */
    @GET("{warehouseId}/products/{productId}")
    Call<ProductStock> getProductStockById(@Path("warehouseId") Long warehouseId, @Path("productId") Long productId);

    /**
     * @param warehouseId - warehouse to which stocks should be filled
     * @param body        - stock up data
     * @return stock up id
     */
    @POST("{warehouseId}/stockups")
    Call<JsonNode> stockup(@Path("warehouseId") Long warehouseId, @Body WarehouseStockUp body);

    /**
     * @param warehouseId - warehouse to which stocks should be transfered
     * @param body        - transfer data
     * @return transfer id
     */
    @POST("{warehouseId}/transfer")
    Call<JsonNode> transfer(@Path("warehouseId") Long warehouseId, @Body WarehouseTransfer body);


    /**
     * @param warehouseId - warehouse in which products should be put on sale
     * @param body        - product sale data
     * @return sale id
     */
    @POST("{warehouseId}/transfer")
    Call<JsonNode> sales(@Path("warehouseId") Long warehouseId, @Body WarehouseItemSale body);
}
