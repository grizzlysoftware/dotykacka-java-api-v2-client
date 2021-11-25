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
