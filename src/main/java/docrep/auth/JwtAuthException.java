package docrep.auth;


import org.springframework.security.core.AuthenticationException;

public class JwtAuthException extends AuthenticationException{
    private static final long serialVersionUID = 1L;

    public JwtAuthException(String msg) {
        super(msg);
    }

}

