package com.example.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        UserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
                    .withClient("api-test-web")
                    .secret(passwordEncoder.encode("web123"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("write", "read")
                    .accessTokenValiditySeconds(15)
                .and()
                    .withClient("app-mobile")
                    .secret(passwordEncoder.encode("mobile@123"))
                    .authorizedGrantTypes("password")
                    .scopes("write", "read")
                    .accessTokenValiditySeconds(60 * 3)
                .and()
                    .withClient("checktoken-test-api")
                    .secret(passwordEncoder.encode("api@123"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
