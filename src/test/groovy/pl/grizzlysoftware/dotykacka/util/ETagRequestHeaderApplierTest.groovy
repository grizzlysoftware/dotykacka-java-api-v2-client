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

package pl.grizzlysoftware.dotykacka.util

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.BufferedSink
import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import pl.grizzlysoftware.util.JacksonProvider
import spock.lang.Specification

class ETagRequestHeaderApplierTest extends Specification {
    ETagRequestHeaderApplier interceptor = new ETagRequestHeaderApplier()

    def "it should add If-Match header if CloudEntity is in request body"() {
        given:
            def cloudEntity = cloudEntity("12333333312137")
            def requestBody = prepareRequestBody(cloudEntity)
            def chain = prepareChain(requestBody)
        when:
            def response = interceptor.intercept(chain)
        then:
            cloudEntity.etag == response.request.header("If-Match")
    }

    def "it should add If-Match header if collection of CloudEntity is in request body"() {
        given:
            def etag = "12333333312137"
            def cloudEntities = [
                    cloudEntity(etag),
                    cloudEntity(etag + "1"),
                    cloudEntity(etag + "2"),
                    cloudEntity(etag + "3"),
            ]
            def requestBody = prepareRequestBody(cloudEntities)
            def chain = prepareChain(requestBody)
        when:
            def response = interceptor.intercept(chain)
        then: "Header should be taken from first entity"
            cloudEntities[0].etag == response.request.header("If-Match")
    }

    def "it should not add If-Match header if non CloudEntity is given"() {
        given:
            def cloudEntities = [
                    new AccessToken(TokenUtils.TOKEN)
            ]
            def requestBody = prepareRequestBody(cloudEntities)
            def chain = prepareChain(requestBody)
        when:
            def response = interceptor.intercept(chain)
        then: "Header should be taken from first entity"
            !response.request.header("If-Match")
    }

    static def cloudEntity(String etag) {
        def cloudEntity = new CloudEntity()
        cloudEntity.etag = etag
        return cloudEntity
    }

    def prepareRequestBody(Object object) {
        def serializedEntity = JacksonProvider.mapper.writeValueAsBytes(object)
        def requestBody = Mock(RequestBody)

        requestBody.writeTo(_) >> { args ->
            (args[0] as BufferedSink).write(serializedEntity)
        }

        return requestBody
    }

    def prepareChain(RequestBody requestBody) {
        def chain = Mock(Interceptor.Chain)
        chain.request() >> new Request.Builder().url("http://localhost").method("POST", requestBody).build()
        chain.proceed(_ as Request) >> { args ->
            return new Response.Builder()
                    .request(args[0])
                    .code(200)
                    .protocol(Protocol.HTTP_1_1)
                    .message("empty")
                    .build()
        }

        return chain
    }
}
