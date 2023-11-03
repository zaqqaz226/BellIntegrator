package com.example.bellintegrator.configuration;

import com.example.bellintegrator.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((rq) -> rq.requestMatchers("/auth")
                        .hasRole("ADMIN"))
                .formLogin((from) -> from.loginPage("/login"))
                .logout().logoutSuccessUrl("/");

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN");
    }

    @Bean
    public UserDetailsService userDetailsService(@Value("user") String userName) {

        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority("ADMIN");

        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();

        roles.add(simpleGrantedAuthority);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "admin",
                "password",
                roles

        );

        return new InMemoryUserDetailsManager(userDetails);
    }

}
