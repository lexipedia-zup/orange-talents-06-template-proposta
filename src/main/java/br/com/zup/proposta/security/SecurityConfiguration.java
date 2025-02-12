package br.com.zup.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_proposta-read")
                .antMatchers(HttpMethod.POST, "/proposta").hasAuthority("SCOPE_proposta-write")
                .antMatchers(HttpMethod.POST, "/bloqueio/**").hasAuthority("SCOPE_proposta-write")
                .antMatchers(HttpMethod.POST, "/aviso/**").hasAuthority("SCOPE_proposta-write")
                .antMatchers(HttpMethod.POST, "/carteira/**").hasAuthority("SCOPE_proposta-write")
                .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_prometheus")
                    .anyRequest().authenticated()
        ).oauth2ResourceServer(
                OAuth2ResourceServerConfigurer::jwt
        );

    }
}
