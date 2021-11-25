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

import pl.grizzlysoftware.dotykacka.client.v2.model.Customer;
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
public interface CustomerService {

    /**
     * @param customers - customers to be created
     * @return Customer
     */
    @POST(" ")
    Call<Collection<Customer>> createCustomers(@Body Collection<Customer> customers);

    /**
     * @param customers - customers to be created or updated
     * @return Customers
     */
    @PUT(" ")
    Call<Collection<Customer>> updateCustomers(@Body Collection<Customer> customers);

    /**
     * @param customerId - id of customer to be updated
     * @param customer   - customer to be updated
     * @return Customer
     */
    @PUT("{customerId}")
    Call<Customer> updateCustomer(@Path("customerId") Long customerId, @Body Customer customer);

    /**
     * @param customerId - id of customer to be patched
     * @param customer   - customer to be patched
     * @return Customer
     */
    @PATCH("{customerId}")
    Call<Customer> patchCustomer(@Path("customerId") Long customerId, @Body Customer customer);

    /**
     * @param customerId - customer id
     * @return Customer
     */
    @DELETE("{customerId}")
    Call<Customer> deleteCustomer(@Path("customerId") Long customerId);

    /**
     * @param customerId - customerId id
     * @return Customer
     */
    @GET("{customerId}")
    Call<Customer> getCustomer(@Path("customerId") Long customerId);

    /**
     * @param page   - pagination parameter, default = 1
     * @param limit  - pagination parameter, default = 100, max = 100
     * @param filter - query filter
     * @param sort   - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return Customers
     */
    @GET(" ")
    Call<ResultPage<Customer>> getCustomers(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("filter") String filter,
            @Query("sort") String sort
    );
}
