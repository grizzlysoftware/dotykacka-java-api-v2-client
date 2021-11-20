package pl.grizzlysoftware.dotykacka.util

import pl.grizzlysoftware.dotykacka.client.v2.model.AccessToken
import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity
import spock.lang.Specification

class ETagCloudEntityApplierTest extends Specification {
    ETagCloudEntityApplier applier = new ETagCloudEntityApplier()

    def "applyEtag - it should apply etag to CloudEntity object"() {
        given:
            def cloudEntity = new CloudEntity()
            def etag = "123333333331"
        when:
            applier.applyEtag(cloudEntity, etag)
        then:
            etag == cloudEntity.etag
    }

    def "applyEtag - it should apply etag to collection of CloudEntity objects"() {
        given:
            def cloudEntities = [
                    new CloudEntity(), new CloudEntity(),
                    new CloudEntity(), new CloudEntity()
            ]
            def etag = "123333333331"
        when:
            applier.applyEtag(cloudEntities, etag)
        then:
            cloudEntities.forEach { assert etag == it.etag }
    }

    def "applyEtag - it should remove unnecessary quotes"() {
        given:
            def cloudEntities = [
                    new CloudEntity(), new CloudEntity(),
                    new CloudEntity(), new CloudEntity()
            ]
            def expectedEtag = "123333x33331"
            def etag = "\"${expectedEtag.replace("x", "\"x")}\""
        when:
            applier.applyEtag(cloudEntities, etag)
        then:
            cloudEntities.forEach { assert expectedEtag == it.etag }
    }

    def "applyEtag - it should not fail on non CloudEntity object"() {
        given:
            def cloudEntities = [
                    new AccessToken(TokenUtils.TOKEN), new Object()
            ]
            def etag = "123333333331"
        when:
            applier.applyEtag(cloudEntities, etag)
        then:
            noExceptionThrown()
        when:
            applier.applyEtag(new Object(), etag)
        then:
            noExceptionThrown()
    }
}
