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
