package com.game.core.config;

import com.game.CustomAuthenticationEntryPoint;
import com.game.SecurityHandler;
import com.game.core.filter.JwtAuthenticationFilter;
import com.game.core.filter.JwtAuthorizationFilter;
import com.game.data.entities.Redirection;
import com.game.data.repository.RedirectionRepository;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityHandler securityHandler;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedirectionRepository redirectionRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
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
//        http.csrf().disable().authorizeRequests().antMatchers("/*").permitAll();
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository));

        http.authorizeRequests()
                .antMatchers("/registration", "/*").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/").hasAuthority("USER");

        redirectionRepository.getDirections().forEach(redirection -> {
            try {
                HttpMethod httpMethod = getHttpMethod(redirection);
                http.authorizeRequests().antMatchers(httpMethod, redirection.getUrl().getLink())
                        .hasAuthority(redirection.getRole().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        .anyRequest().authenticated()
        http.authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.html", true).permitAll()
                .and().logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .and().rememberMe().tokenValiditySeconds(604800).key("mySecret")
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    private HttpMethod getHttpMethod(Redirection redirection) {
        switch (redirection.getAction().getMethod()) {
            case "POST":
                return HttpMethod.POST;
            case "GET":
                return HttpMethod.GET;
            case "PUT":
                return HttpMethod.PUT;
            case "DELETE":
                return HttpMethod.DELETE;
            case "HEAD":
                return HttpMethod.HEAD;
            case "OPTIONS":
                return HttpMethod.OPTIONS;
            case "PATCH":
                return HttpMethod.PATCH;
            case "TRACE":
                return HttpMethod.TRACE;
            default:
                return null;
        }
    }

}
