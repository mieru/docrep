package docrep.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final Log logger = LogFactory.getLog(this.getClass());
    private AntPathRequestMatcher requestMatcher;


    public JwtAuthenticationTokenFilter(AntPathRequestMatcher antPathRequestMatcher) {
        this.requestMatcher = antPathRequestMatcher;
    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        try {
            DecodedJWT decodedJWT = jwtTokenUtil.validateToken(token);
            if (decodedJWT == null) {
                logger.debug("Token not correct");
                throw new JwtAuthException("Token not correct");
            }

            Authentication auth = new JwtAuthenticationToken(token, decodedJWT);
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);

        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            jwtAuthenticationEntryPoint.commence(request, response, authenticationException);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !requestMatcher.matches(request);
    }

}

