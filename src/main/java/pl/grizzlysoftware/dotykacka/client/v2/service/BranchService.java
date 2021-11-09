package pl.grizzlysoftware.dotykacka.client.v2.service;

import pl.grizzlysoftware.dotykacka.client.v2.model.Branch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface BranchService {
    /**
     * @param cloudId - cloud id
     * @param branchId - branch id
     * @return
     */
    @GET("{cloudId}/branches/{branchId}")
    Call<Branch> getBranch(@Path("cloudId") Long cloudId, @Path("branchId") Long branchId);

    /**
     * @param cloudId   - cloud id
     * @param offset    - pagination parameter, default = 0
     * @param limit     - pagination parameter, default = 100, max = 100
     * @param filter    - query filter
     * @param sort      - Description: Sort parameters in format: column_name1,column_name2 //means asc -column_name1,-column_name2 //means desc
     * @return
     */
    @GET("{cloudId}/branches")
    Call<Collection<Branch>> getBranches(@Path("cloudId") Long cloudId,
                                         @Query("limit") int limit,
                                         @Query("offset") int offset,
                                         @Query("filter") String filter,
                                         @Query("sort") String sort);
}
