package docrep.auth;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


@Service
public class JwtTokenUtil {
    private static final String USERNAME_CLAIM = "username";
    private static final String SECRED_KEY = "docrep-cezary-miernik-2018-psk";

    private Algorithm algorithm = null;

    public JwtTokenUtil() throws IllegalArgumentException, UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(Base64.getEncoder().encodeToString(SECRED_KEY.getBytes()));
    }

    public String generateToken(String username) {
        JWTCreator.Builder jwtBuilder = JWT.create();
        return jwtBuilder.withClaim(USERNAME_CLAIM, username).sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        DecodedJWT decodedJWT;
        try {
            if (token == null) {
                throw new JWTVerificationException("Auth token is null");
            }
            JWTVerifier jwtVerification = JWT.require(algorithm).build();
            decodedJWT = jwtVerification.verify(token);
        }
        catch (JWTVerificationException e) {
            return null;
        }
        return decodedJWT;
    }

    public String getUsernameFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(USERNAME_CLAIM).asString();
    }


}

