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
