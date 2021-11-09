package pl.grizzlysoftware.dotykacka.client.v2;

import pl.grizzlysoftware.dotykacka.client.v2.facade.AuthenticationServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.BranchServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.service.AuthenticationService;
import pl.grizzlysoftware.dotykacka.client.v2.service.BranchService;
import pl.grizzlysoftware.dotykacka.util.AccessTokenProvider;
import pl.grizzlysoftware.dotykacka.util.TokenRenewingRequestInterceptor;
import pl.grizzlysoftware.util.OkHttpLoggingInterceptor;

import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;
import static pl.grizzlysoftware.util.OkHttpClientUtils.builder;
import static pl.grizzlysoftware.util.RetrofitUtils.service;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 * <p>
 * Dotykacka API V2 client class
 */
public class DotykackaApiClient {
    public final AuthenticationService authenticationService;
    public final AuthenticationServiceFacade authenticationServiceFacade;

    public final BranchService branchService;
    public final BranchServiceFacade branchServiceFacade;

    public DotykackaApiClient(ApiConfiguration configuration) {
        checkNotNull(configuration, "20211106:084700", "ApiConfiguration cannot be null");
        final var httpClient = builder()
                .callTimeout(configuration.requestTimeout)
                .addInterceptor(new OkHttpLoggingInterceptor())
                .build();

        authenticationService = service(httpClient, configuration.url, AuthenticationService.class);
        authenticationServiceFacade = new AuthenticationServiceFacade(
                authenticationService,
                configuration.cloudId,
                configuration.refreshToken
        );

        final var reqHttpClient = builder()
                .callTimeout(configuration.requestTimeout)
                .addInterceptor(new TokenRenewingRequestInterceptor(new AccessTokenProvider(authenticationServiceFacade)))
                .build();

        branchService = service(reqHttpClient, configuration.url, BranchService.class);
        branchServiceFacade = new BranchServiceFacade(
                branchService,
                configuration.cloudId
        );
    }
}
