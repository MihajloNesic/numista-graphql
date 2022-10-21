package io.gitlab.mihajlonesic.numistagraphql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = SecurityPackage.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApiKeyEntryPoint apiKeyEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;

    public static final String KEY_HEADER = "x-api-key";

    private final List<String> NOT_SECURED = Arrays.asList(
            "/graphiql/**",
            "/subscriptions/**",
            "/h2/**",
            "/favicon.ico",
            "/vendor/**"
    );

    @Bean
    public ApiKeyFilter apiKeyFilter() {
        return new ApiKeyFilter(apiKeyEntryPoint, NOT_SECURED);
    }

    protected PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // remove csrf and state in session
                .cors().and().csrf().disable()
                // entry point
                .exceptionHandling().authenticationEntryPoint(apiKeyEntryPoint)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                // add api key filter
                .addFilterBefore(apiKeyFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();
    }

}

