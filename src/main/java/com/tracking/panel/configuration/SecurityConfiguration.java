
package com.tracking.panel.configuration;


import com.tracking.panel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAppUserDetailsService myAppUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/dist/**","/bootstrap/**","/plugins/**","/font-awesome/**","/static/**").permitAll()
                .and().formLogin().loginPage("/").permitAll().loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/?error=true")
                .and().logout()    //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        .and().authorizeRequests().antMatchers("/**").hasAuthority("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .csrf();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/access-denied"); // #3
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
    }

}
