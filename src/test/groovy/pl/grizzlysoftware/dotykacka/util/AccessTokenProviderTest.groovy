/*
 * Copyright 2019 Grizzly Software, https://grizzlysoftware.pl
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
 */

package pl.grizzlysoftware.dotykacka.util

import pl.grizzlysoftware.dotykacka.client.v2.facade.AuthenticationServiceFacade
import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken
import pl.grizzlysoftware.dotykacka.util.exception.IdNullPointerException
import spock.lang.Specification

import static pl.grizzlysoftware.dotykacka.util.TokenUtils.EXPIRED_TOKEN
import static pl.grizzlysoftware.dotykacka.util.TokenUtils.TOKEN

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class AccessTokenProviderTest extends Specification {

    def "throws IdNullPointerException when given args are null"() {
        when:
            new AccessTokenProvider(null)
        then:
            thrown(IdNullPointerException)
    }

    def "returns access token"() {
        given:
            def authenticationService = Mock(AuthenticationServiceFacade)
            authenticationService.accessToken() >> new AccessToken(TOKEN)
            def provider = new AccessTokenProvider(authenticationService)
        when:
            null == provider.accessToken
            def accessToken = provider.acquireToken()
        then:
            null != accessToken
            null != accessToken.token
            TOKEN == accessToken.token
    }

    def "returns same access token if it's still valid"() {
        given:
            def authenticationService = Mock(AuthenticationServiceFacade)
            authenticationService.accessToken() >> new AccessToken(TOKEN)
            def provider = new AccessTokenProvider(authenticationService)
        when:
            def accessToken0 = provider.acquireToken()
            def accessToken1 = provider.acquireToken()
        then:
            null != accessToken0
            null != accessToken1
            accessToken0 == accessToken1
    }

    def "returns refreshed access token"() {
        given:
            def expiredToken = new AccessToken(EXPIRED_TOKEN)
            def validToken = new AccessToken(TOKEN)
            def authenticationService = Mock(AuthenticationServiceFacade)
            authenticationService.accessToken() >>> [
                    expiredToken,   //expired token
                    validToken]
            def provider = new AccessTokenProvider(authenticationService)
        when:
            def accessToken0 = provider.acquireToken()
        then:
            null != accessToken0
            expiredToken == accessToken0
        when:
            def accessToken1 = provider.acquireToken()
        then:
            null != accessToken1
            validToken == accessToken1
    }
}