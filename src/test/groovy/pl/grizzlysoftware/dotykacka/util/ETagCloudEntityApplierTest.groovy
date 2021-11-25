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
