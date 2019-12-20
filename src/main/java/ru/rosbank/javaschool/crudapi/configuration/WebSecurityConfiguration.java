package ru.rosbank.javaschool.crudapi.configuration;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/ws",
                "/",
                "/csrf",
                "/v2/api-docs",
                "/configuration/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // url based-security
        http
                .csrf().disable() // csrf по умолчанию включен Cross Site Request Forgery
                .cors()
                .and()
                // REST -> Stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Spring Security - ищет первое совпадение по правилу
                .authorizeRequests()
                // .antMatchers -> Ant: * - всё, но не /, ** - всё, включая /
                .antMatchers("/error").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api").permitAll()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/registration").permitAll()
                .antMatchers(HttpMethod.GET,"/api/registration").permitAll()
                .antMatchers(HttpMethod.GET,  "/api/posts/search").permitAll()
                .antMatchers(HttpMethod.POST,"/api/users/registration").permitAll()
                .antMatchers(HttpMethod.GET,"/api").permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts", "/api/search").permitAll()
                .antMatchers(HttpMethod.GET, "/api/files/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/files/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/me").authenticated()
                .antMatchers( "/*").authenticated()
                .antMatchers( "/api/*").authenticated()


                // ROLE_ -> authority
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/api/posts/edit").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.POST);
        source.registerCorsConfiguration(
                "/**",
                configuration
        );
        return source;
    }
}
