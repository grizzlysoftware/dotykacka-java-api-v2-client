package pl.grizzlysoftware.util;

import retrofit2.Call;
import retrofit2.Response;

public interface OnRetrofitCallExecutionListener {
    <T> void onBeforeExecution(Call<T> call);

    <T> void onAfterExecution(Call<T> call, Response<T> response);

    <T> void onExecutionSuccessful(Call<T> call, Response<T> response);
}
