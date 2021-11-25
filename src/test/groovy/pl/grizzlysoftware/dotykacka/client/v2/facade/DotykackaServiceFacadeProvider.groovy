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

package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.ApiConfiguration
import pl.grizzlysoftware.dotykacka.client.v2.DotykackaApiClient

import java.time.Duration
import java.util.function.Function

class DotykackaServiceFacadeProvider<T extends DotykackaApiService<?>> {
    final DotykackaApiClient client
    final ApiConfiguration configuration

    DotykackaServiceFacadeProvider() {
        def url = System.getenv("DOTYKACKA_API_URL")
        def token = System.getenv("DOTYKACKA_API_TOKEN")
        def cloudId = System.getenv("DOTYKACKA_API_CLOUD_ID") as Long

        configuration = new ApiConfiguration(
                url,
                token,
                cloudId,
                Duration.ofSeconds(5)
        )
        client = new DotykackaApiClient(configuration)
    }

    T getServiceFacade(Function<DotykackaApiClient, T> extractor) {
        return extractor.apply(client)
    }
}
