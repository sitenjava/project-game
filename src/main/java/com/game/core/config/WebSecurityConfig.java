package com.game.core.config;

import com.game.JwtAuthenticationFilter;
import com.game.JwtAuthorizationFilter;
import com.game.SecurityHandler;
import com.game.data.repository.ActionRepository;
import com.game.data.repository.RedirectionRepository;
import com.game.data.repository.RoleRepository;
import com.game.data.repository.UserRepository;
import com.game.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private RedirectionRepository redirectionRepository;

    @Autowired
    SecurityHandler securityHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository));

        http.authorizeRequests()
                .antMatchers("/registration", "/").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/").hasAuthority("USER");

        roleRepository.getAllRoleNames().forEach(role -> {
            /*actionRepository.getAllActionMethods().forEach(method -> {
                redirectionRepository.getLinks(role, method).forEach(link -> {
                    try {
                        http.authorizeRequests().antMatchers(HttpMethod.POST, link).hasAuthority(role);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });*/
            redirectionRepository.getLinks(role, "POST").forEach(link -> {
                try {
                    http.authorizeRequests().antMatchers(HttpMethod.POST, link).hasAuthority(role);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            redirectionRepository.getLinks(role, "GET").forEach(link -> {
                try {
                    System.out.println(link);
                    http.authorizeRequests().antMatchers(HttpMethod.GET, link).hasAuthority(role);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            redirectionRepository.getLinks(role, "PUT").forEach(link -> {
                try {
                    http.authorizeRequests().antMatchers(HttpMethod.PUT, link).hasAuthority(role);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            redirectionRepository.getLinks(role, "DELETE").forEach(link -> {
                try {
                    http.authorizeRequests().antMatchers(HttpMethod.DELETE, link).hasAuthority(role);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/index", true).permitAll()
                .and().logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .and().rememberMe().tokenValiditySeconds(604800).key("mySecret");
    }

}
