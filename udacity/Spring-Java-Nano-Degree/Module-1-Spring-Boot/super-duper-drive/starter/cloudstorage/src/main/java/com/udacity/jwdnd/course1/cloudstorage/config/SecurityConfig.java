package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.udacity.jwdnd.course1.cloudstorage.security.AuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
         throws Exception{
        auth
        .authenticationProvider(this.authenticationService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .antMatchers( "/signup","/h2/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated());

        http.csrf(csrf -> csrf.ignoringAntMatchers("/h2/**"));
        http.headers(headers -> headers.frameOptions().sameOrigin());

        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/logout") // Specify the logout URL
                .logoutSuccessUrl("/login?logout") // Redirect to login with logout parameter on success
                .permitAll()); // Allow all users to access the logout URL
    }
    
}
