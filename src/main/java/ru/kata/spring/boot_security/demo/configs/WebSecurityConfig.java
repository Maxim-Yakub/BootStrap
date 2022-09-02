package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    private final SuccessUserHandler successUserHandler;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsService userService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/styles/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()

                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/").successHandler(successUserHandler)
                .permitAll()

                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }

}