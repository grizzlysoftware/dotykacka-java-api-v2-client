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
