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

package pl.grizzlysoftware.dotykacka.client.v2;

import java.time.Duration;

import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkArgument;
import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 *
 * Configuration class for {@link pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient}
 */
public class ApiConfiguration {
    /** api base-url */
    public final String url;

    /** cloud identifier to be accessed */
    public final Long cloudId;

    /** refresh token obtained via OAuth2 api, @see {https://docs.api.dotypos.com/authorization#get-access-token}*/
    public final String refreshToken;

    /** timeout for requests */
    public final Duration requestTimeout;

    public ApiConfiguration(String url, String refreshToken, Long cloudId, Duration requestTimeout) {
        checkNotNull(url, "20211106:065450", "url cannot be null");
        checkArgument(url.isBlank(), "20211106:065450", "url cannot be blank");
        checkNotNull(cloudId, "20211106:064700", "cloudId cannot be null");
        checkNotNull(refreshToken, "20211106:064740", "refreshToken cannot be null");
        checkArgument(refreshToken.isBlank(), "20211106:064741", "refreshToken cannot be blank");

        this.url = url;
        this.cloudId = cloudId;
        this.refreshToken = refreshToken;
        this.requestTimeout = requestTimeout != null ? requestTimeout : Duration.ofSeconds(60);
    }
}
