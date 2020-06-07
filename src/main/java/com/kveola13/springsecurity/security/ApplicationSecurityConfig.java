package com.kveola13.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**")
                .hasRole(ApplicationUserRole.Wolf.name())
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
                .roles(ApplicationUserRole.Wolf.name())
                .build();

        UserDetails bardUser = User.builder()
                .username("Jaskier")
                .password(passwordEncoder.encode("Wine"))
                .roles(ApplicationUserRole.Bard.name())
                .build();

        UserDetails newbUser = User.builder()
                .username("Ciri")
                .password(passwordEncoder.encode("Portal"))
                .roles(ApplicationUserRole.Newb.name())
                .build();

        return new InMemoryUserDetailsManager(adminUser, bardUser, newbUser);
    }
}
