package docrep.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String token;
    private DecodedJWT decodedJWT;

    public JwtAuthenticationToken(String token, DecodedJWT decodedJWT) {
        super(null, null);
        this.token = token;
        this.decodedJWT = decodedJWT;
    }

    public DecodedJWT getDecodedJWT() {
        return decodedJWT;
    }

    public void setDecodedJWT(DecodedJWT decodedJWT) {
        this.decodedJWT = decodedJWT;
    }


    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}

