package com.kveola13.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.kveola13.springsecurity.security.ApplicationUserPermission.*;
import static com.kveola13.springsecurity.security.ApplicationUserRole.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**")
                .hasRole(Newb.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adminUser = User
                .builder()
                .username("Geralt")
                .password(passwordEncoder.encode("Rivia"))
                .authorities(Wolf.getGrantedAuthorities())
                .build();

        UserDetails bardUser = User.builder()
                .username("Jaskier")
                .password(passwordEncoder.encode("Wine"))
                .authorities(Bard.getGrantedAuthorities())
                .build();

        UserDetails newbUser = User.builder()
                .username("Ciri")
                .password(passwordEncoder.encode("Portal"))
                .authorities(Newb.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(adminUser, bardUser, newbUser);
    }
}
