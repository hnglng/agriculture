package com.sannong.project.presentation.config;

import com.sannong.project.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bright Huang on 1/29/15.
 */


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties security;

    @Autowired
    private DataSource datasource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        userDetailsService.setDataSource(datasource);
        // TODO: MD5 Encoder was deprecated, should change to PasswordEncoder encoder = new BCryptPasswordEncoder();
        PasswordEncoder encoder = new Md5PasswordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        auth.jdbcAuthentication().dataSource(datasource);

        /*
        if(!userDetailsService.userExists("user")) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            User userDetails = new User("user", encoder.encode("password"), authorities);

            userDetailsService.createUser(userDetails);
        }
        */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // TODO: Need to enable csrf, http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#jc-form
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/home ").permitAll()
                .antMatchers("/application-form").permitAll()
                //.antMatchers("/user-center").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/user-center/**").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                        //.loginPage("/login")
                .loginPage("/home")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.datasource);
    }

}