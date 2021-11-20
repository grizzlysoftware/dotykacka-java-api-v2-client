package pl.grizzlysoftware.dotykacka.client.v2.facade

import spock.lang.Requires


class AuthenticationServiceFacadeTest extends EnvSpecification {
    AuthenticationServiceFacade service

    def setup() {
        def provider = new DotykackaServiceFacadeProvider()
        service = provider.client.authenticationServiceFacade
    }

    @Requires({ env.INTEGRATION_TEST })
    def "get - it should authenticate successfully"() {
        when:
            def result = service.accessToken()
        then:
            result.token
            result.decodedToken
            !result.isExpired()
    }
}
