package com.piotrba.charity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/resources/css/**", "/resources/js/**", "/resources/images/**").permitAll()
                .antMatchers("/homepage").permitAll()
                .antMatchers("/form").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register/user").permitAll()
                .antMatchers("/admin-profile-categories/**").hasRole("ADMIN")
                .antMatchers("/admin-profile-donations/**").hasRole("ADMIN")
                .antMatchers("/admin-profile-institutions/**").hasRole("ADMIN")
                .antMatchers("/admin-profile-users/**").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/authLogin", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }
}
