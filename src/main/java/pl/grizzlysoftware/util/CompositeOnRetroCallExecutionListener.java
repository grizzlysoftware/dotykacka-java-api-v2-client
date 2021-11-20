package pl.grizzlysoftware.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Collection;

public class CompositeOnRetroCallExecutionListener implements OnRetrofitCallExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(CompositeOnRetroCallExecutionListener.class);

    protected final Collection<OnRetrofitCallExecutionListener> listeners;

    public CompositeOnRetroCallExecutionListener(Collection<OnRetrofitCallExecutionListener> listeners) {
        this.listeners = ExceptionPreconditions.checkNotNull(listeners, "20211118:215816", "Collection<OnRetrofitCallExecutionListener> cannot be null");
    }

    @Override
    public <T> void onBeforeExecution(Call<T> call) {
        listeners.forEach(e -> invokeOnBeforeExecution(e, call));
    }

    @Override
    public <T> void onAfterExecution(Call<T> call, Response<T> response) {
        listeners.forEach(e -> invokeOnAfterExecution(e, call, response));
    }

    @Override
    public <T> void onExecutionSuccessful(Call<T> call, Response<T> response) {
        listeners.forEach(e -> invokeOnExecutionSuccessful(e, call, response));
    }

    protected <T> void invokeOnBeforeExecution(OnRetrofitCallExecutionListener listener, Call<T> call) {
        try {
            listener.onBeforeExecution(call);
        } catch (Exception e) {
            logger.warn("invokeOnBeforeExecution failed on '{}'\n{}", listener.getClass(), e);
        }
    }

    protected <T> void invokeOnAfterExecution(OnRetrofitCallExecutionListener listener, Call<T> call, Response<T> response) {
        try {
            listener.onExecutionSuccessful(call, response);
        } catch (Exception e) {
            logger.warn("invokeOnAfterExecution failed on '{}'\n{}", listener.getClass(), e);
        }
    }

    protected <T> void invokeOnExecutionSuccessful(OnRetrofitCallExecutionListener listener, Call<T> call, Response<T> response) {
        try {
            listener.onExecutionSuccessful(call, response);
        } catch (Exception e) {
            logger.warn("invokeOnExecutionSuccessful failed on '{}'\n{}", listener.getClass(), e);
        }
    }
}
