package docrep.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(corsFilter(), JwtAuthenticationTokenFilter.class);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationFilter() throws Exception {
        String REQUIRES_AUTH_PATH = "/api/**";
        return new JwtAuthenticationTokenFilter(new AntPathRequestMatcher(REQUIRES_AUTH_PATH));
    }

    @Bean
    public CorsFilter corsFilter() throws Exception {
        return new CorsFilter();
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() throws Exception {
        return new JwtAuthenticationProvider();
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationFailureHandler() throws Exception {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() throws Exception {
        return new BCryptPasswordEncoder();
    }


}
