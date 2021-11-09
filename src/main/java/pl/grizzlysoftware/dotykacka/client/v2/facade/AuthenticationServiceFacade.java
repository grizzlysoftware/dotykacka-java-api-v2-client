package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken;
import pl.grizzlysoftware.dotykacka.client.v2.model.Cloud;
import pl.grizzlysoftware.dotykacka.client.v2.service.AuthenticationService;

import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;

public class AuthenticationServiceFacade extends DotykackaApiService<AuthenticationService> {
    private final String refreshToken;

    public AuthenticationServiceFacade(AuthenticationService service, Long cloudId, String refreshToken) {
        super(service, cloudId);
        this.refreshToken = "User " + checkNotNull(refreshToken, "20211108:145429", "refreshToken cannot be null");
    }

    public AccessToken accessToken() {
        return execute(service.accessToken(new Cloud(cloudId), refreshToken));
    }
}
