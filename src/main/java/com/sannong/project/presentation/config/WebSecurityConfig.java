package com.sannong.project.presentation.config;

import com.sannong.project.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Bright Huang on 1/29/15.
 */

@Configuration
public class WebSecurityConfig extends GlobalAuthenticationConfigurerAdapter {


//    @Configuration
//    public static class FormWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring().antMatchers("/static/**","/status");
//            web.ignoring().antMatchers("/**/*.jsp");
//        }
//
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().hasRole("USER").and()
//                    .formLogin().loginPage("/login").permitAll();
//        }
//    }

//    @Autowired
//    private UserRepository userRepository;

    /*
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
    */

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> userRepository.findByUsername(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
    */
}