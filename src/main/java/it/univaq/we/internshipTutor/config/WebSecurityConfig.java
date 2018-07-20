package it.univaq.we.internshipTutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // allow semicolons in url
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**" , "/js/**", "/images/**", "/fonts/**", "/plugins/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/index", "/register/**").permitAll()
                    .antMatchers("/admin/**").permitAll()
                    //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/student/**").permitAll()
                    //.antMatchers("/student/**").hasAnyRole("STUDENT")
                    .antMatchers("/company/**").permitAll()
                    //.antMatchers("/company/**").hasAnyRole("COMPANY")
                .anyRequest().authenticated()
                .and()
                .logout()
                    .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

}