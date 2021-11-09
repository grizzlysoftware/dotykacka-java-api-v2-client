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
