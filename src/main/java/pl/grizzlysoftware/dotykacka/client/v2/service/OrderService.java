package pl.grizzlysoftware.dotykacka.client.v2.service;

import pl.grizzlysoftware.dotykacka.client.v2.model.Order;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface OrderService {
    /**
     * @param orderId - order id
     * @return Order
     */
    @GET("{orderId}")
    Call<Order> getOrder(@Path("orderId") Long orderId);

    /**
     * @param page        - pagination parameter, default = 1
     * @param limit       - pagination parameter, default = 100, max = 100
     * @param include     - includes specified related entities i.e. moneyLogs or/and orderItems: "include=orderItems,moneyLogs"
     * @param namedFilter - query filter
     * @param filter      - query filter
     * @param sort        - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return Orders
     */
    @GET(" ")
    Call<ResultPage<Order>> getOrders(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("include") String include,
            @Query("namedFilter") String namedFilter,
            @Query("filter") String filter,
            @Query("sort") String sort);
}
