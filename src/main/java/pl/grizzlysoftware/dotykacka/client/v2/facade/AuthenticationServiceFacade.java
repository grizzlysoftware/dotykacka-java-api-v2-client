package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken;
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudIdWrapper;
import pl.grizzlysoftware.dotykacka.client.v2.service.AuthenticationService;
import pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions;

import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;

public class AuthenticationServiceFacade extends DotykackaApiService<AuthenticationService> {
    private final String refreshToken;
    private final Long cloudId;

    public AuthenticationServiceFacade(AuthenticationService service, Long cloudId, String refreshToken) {
        super(service);
        this.cloudId = ExceptionPreconditions.checkNotNull(cloudId, "20211109:204916", "cloudId cannot be null");
        this.refreshToken = "User " + checkNotNull(refreshToken, "20211108:145429", "refreshToken cannot be null");
    }

    public AccessToken accessToken() {
        return execute(service.accessToken(new CloudIdWrapper(cloudId), refreshToken));
    }
}
