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

import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import org.apache.commons.lang3.time.DateUtils

import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class TokenUtils {
    public static final def KEY_PAIR = KeyPairGenerator.getInstance("RSA")
            .generateKeyPair()

    public static final def EXPIRED_TOKEN = new JWTCreator.Builder()
            .withIssuedAt(DateUtils.addDays(new Date(), -2))
            .withIssuer("test-issuer")
            .withSubject("test-subject")
            .withExpiresAt(DateUtils.addDays(new Date(), -1))
            .sign(Algorithm.RSA512((RSAPublicKey) KEY_PAIR.public, (RSAPrivateKey) KEY_PAIR.private))

    public static final def TOKEN = new JWTCreator.Builder()
            .withIssuedAt(new Date())
            .withIssuer("test-issuer")
            .withSubject("test-subject")
            .withExpiresAt(DateUtils.addDays(new Date(), 1))
            .sign(Algorithm.RSA512((RSAPublicKey) KEY_PAIR.public, (RSAPrivateKey) KEY_PAIR.private))
}
