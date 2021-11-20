package pl.grizzlysoftware.dotykacka.client.v2.service;

import pl.grizzlysoftware.dotykacka.client.v2.model.OrderItem;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface OrderItemService {
    /**
     * @param orderItemId - orderItem id
     * @return OrderItem
     */
    @GET("{orderItemId}")
    Call<OrderItem> getOrderItem(@Path("orderItemId") Long orderItemId);

    /**
     * @param page   - pagination parameter, default = 1
     * @param limit  - pagination parameter, default = 100, max = 100
     * @param filter - query filter
     * @param sort   - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return OrderItems
     */
    @GET(" ")
    Call<ResultPage<OrderItem>> getOrderItems(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("filter") String filter,
            @Query("sort") String sort
    );
}
