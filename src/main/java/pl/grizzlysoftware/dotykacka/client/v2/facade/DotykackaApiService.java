package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.util.BatchLoader;
import pl.grizzlysoftware.util.RetrofitApiService;
import pl.grizzlysoftware.util.RetrofitCallExecutor;

import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 *
 * Wrapper for Retrofit service client that stores cloudId for convenience
 */
public class DotykackaApiService<T> extends RetrofitApiService {
    protected final T service;
    protected final BatchLoader batchLoader;


    public DotykackaApiService(T service, RetrofitCallExecutor executor) {
        super(executor);
        this.service = checkNotNull(service, "20211108:145853", "service cannot be null");
        this.batchLoader = new BatchLoader(100);
    }

    public DotykackaApiService(T service) {
        this.service = checkNotNull(service, "20211108:145853", "service cannot be null");
        this.batchLoader = new BatchLoader(100);
    }
}