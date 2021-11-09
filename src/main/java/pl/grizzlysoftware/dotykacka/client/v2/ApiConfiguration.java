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
