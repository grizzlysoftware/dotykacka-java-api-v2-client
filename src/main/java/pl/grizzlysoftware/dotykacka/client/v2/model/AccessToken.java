package pl.grizzlysoftware.dotykacka.client.v2.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkArgument;
import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;

/**
 * Response body returned by AuthenticationServiceClient
 */
public class AccessToken {

    /**
     * Encoded User access token
     */
    public final String token;

    @JsonIgnore
    private final DecodedJWT decodedToken;

    @JsonCreator
    public AccessToken(@JsonProperty("accessToken") String token) {
        checkNotNull(token, "20211106:071524", "accessToken cannot be null");
        checkArgument(token.isBlank(), "20211106:071600", "accessToken cannot be blank");
        this.token = token;
        this.decodedToken = JWT.decode(token);
    }

    public String subject() {
        return decodedToken.getSubject();
    }

    public String issuer() {
        return decodedToken.getIssuer();
    }

    public Date issuedAt() {
        return decodedToken.getIssuedAt();
    }

    public Date expiresAt() {
        return decodedToken.getExpiresAt();
    }

    public boolean isExpired() {
        return expiresAt().toInstant().isBefore(new Date().toInstant());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AccessToken that = (AccessToken) o;

        return new EqualsBuilder()
                .append(token, that.token)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(token)
                .toHashCode();
    }
}
