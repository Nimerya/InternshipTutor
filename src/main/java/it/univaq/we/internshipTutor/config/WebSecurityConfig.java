package it.univaq.we.internshipTutor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

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
                    .antMatchers("/", "/index", "/register/**", "/internships").permitAll()
                    //.antMatchers("/admin/**").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    //.antMatchers("/student/**").permitAll()
                    .antMatchers("/student/**").hasAnyRole("STUDENT")
                    //.antMatchers("/company/**").permitAll()
                    .antMatchers("/company/**").hasAnyRole("COMPANY")
                    .antMatchers(HttpMethod.POST, "/internship/**").hasAnyRole("STUDENT")
                    .antMatchers("/download/agreement/**").hasAnyRole("COMPANY", "ADMIN")
                    .antMatchers("/download/finalreport/**").hasAnyRole("COMPANY", "ADMIN", "STUDENT")
                    .antMatchers("/download/trainingproject/**").hasAnyRole("COMPANY", "ADMIN", "STUDENT")
                    .antMatchers("/register/**").hasAnyRole("ANONYMOUS")
                .anyRequest().permitAll()
                .and()
                .csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/error?code=403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

}