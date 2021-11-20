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
