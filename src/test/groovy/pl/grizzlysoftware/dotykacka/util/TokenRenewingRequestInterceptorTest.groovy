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

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken
import spock.lang.Specification

import static pl.grizzlysoftware.dotykacka.util.TokenUtils.TOKEN

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class TokenRenewingRequestInterceptorTest extends Specification {

    def "adds token as Authorization header"() {
        given:
            def token = new AccessToken(TOKEN)
            def tokenProvider = Mock(AccessTokenProvider)
            tokenProvider.acquireToken() >> token
            def interceptor = new TokenRenewingRequestInterceptor(tokenProvider)
            def chain = Mock(Interceptor.Chain)
            chain.request() >> new Request.Builder().url("http://localhost").method("GET", null).build()
            chain.proceed(_ as Request) >> { args ->
                return new Response.Builder()
                        .request(args[0])
                        .code(200)
                        .protocol(Protocol.HTTP_1_1)
                        .message("empty")
                        .build()
            }
        when:
            def response = interceptor.intercept(chain)
        then:
            def header = response.request.header("Authorization")
            "Bearer ${token.token}" == header
    }
}
