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

package pl.grizzlysoftware.util

import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import spock.lang.Specification

class RetrofitCallExecutorTest extends Specification {

    def "throws NullPointerException when target is null"() {
        when:
            new RetrofitCallExecutor(null)
        then:
            thrown(NullPointerException)
    }

    def "throws NullPointerException when call is null"() {
        given:
            def executor = new RetrofitCallExecutor(Specification)
        when:
            executor.executeOrThrow(null)
        then:
            thrown(NullPointerException)
    }

    def "throws ResponseStatusException when call response status code is different than 200"(int statusCode, int expectedStatusCode) {
        given:
            def executor = new RetrofitCallExecutor(Specification)
        when:
            executor.executeOrThrow(errorStubCall(statusCode))
        then:
            def exception = thrown(ResponseStatusException)
            exception.statusCode == expectedStatusCode
        where:
            statusCode                            || expectedStatusCode
            HttpURLConnection.HTTP_FORBIDDEN      || HttpURLConnection.HTTP_FORBIDDEN
            HttpURLConnection.HTTP_UNAUTHORIZED   || HttpURLConnection.HTTP_UNAUTHORIZED
            HttpURLConnection.HTTP_INTERNAL_ERROR || HttpURLConnection.HTTP_INTERNAL_ERROR
            HttpURLConnection.HTTP_UNAVAILABLE    || HttpURLConnection.HTTP_UNAVAILABLE
            HttpURLConnection.HTTP_NOT_FOUND      || HttpURLConnection.HTTP_NOT_FOUND
            900                                   || 900                                     //this is just to ensure, that executor is trying to mimic status from response retrieved from external service
    }

    def "proceeds when response is sort of success"(int statusCode, int expectedStatusCode) {
        given:
            def executor = new RetrofitCallExecutor(Specification)
        when:
            def result = executor.executeOrThrow(okStubCall(statusCode))
        then:
            result.code() == expectedStatusCode
        where:
            statusCode                        || expectedStatusCode
            HttpURLConnection.HTTP_OK         || HttpURLConnection.HTTP_OK
            HttpURLConnection.HTTP_ACCEPTED   || HttpURLConnection.HTTP_ACCEPTED
            HttpURLConnection.HTTP_CREATED    || HttpURLConnection.HTTP_CREATED
            HttpURLConnection.HTTP_NO_CONTENT || HttpURLConnection.HTTP_NO_CONTENT
            299                               || 299                                          //this is just to ensure, that executor is mimicing response from response retrieved from external service
    }

    def "given target class is equal to the given one"(Class target) {
        given:
            def executor = new RetrofitCallExecutor(target)
        expect:
            executor.target == target
        where:
            target << [Specification, RetrofitCallExecutorTest]
    }

    HttpUrl urlStub(String url) {
        return new HttpUrl("http://", "", "", "localhost", 8080, [], [], "", url)
    }

    Request requestStub(String url, String method) {
        def request = new Request(
                urlStub(url),
                method,
                new Headers([] as String[]),
                new FormBody([], []),
                [:]
        )
        return request
    }

    Call okStubCall(int statusCode) {
        def call = Mock(Call)
        call.execute() >> Response.success(statusCode, json())
        return call
    }

    Call errorStubCall(int statusCode) {
        def call = Mock(Call)
        call.request() >> requestStub("https://localhost:8080/test", "GET")
        call.execute() >> Response.error(statusCode, json())
        return call
    }

    ResponseBody json() {
        return json("{}")
    }

    ResponseBody json(String json) {
        return ResponseBody.create(json, MediaType.parse("application/json"))
    }
}
