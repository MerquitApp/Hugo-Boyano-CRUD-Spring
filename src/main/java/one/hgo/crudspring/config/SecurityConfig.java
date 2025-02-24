package one.hgo.crudspring.config;

import one.hgo.crudspring.filter.JWTAuthorizationFilter;
import one.hgo.crudspring.handler.OAuth2SuccessHandler;
import one.hgo.crudspring.service.CustomOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final List<AntPathRequestMatcher> excludedUrls = List.of(
            new AntPathRequestMatcher("/static/**"),
            new AntPathRequestMatcher("/auth/login/**"),
            new AntPathRequestMatcher("/auth/register/**")
    );

    @Autowired
    private CustomOauth2UserService customOauth2UserService;

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(this.excludedUrls.toArray(AntPathRequestMatcher[]::new)).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.loginPage("/auth/login/form"))
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/auth/login/github")
                        .successHandler(this.oAuth2SuccessHandler)
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(this.customOauth2UserService)
                        ))
                .build();
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter() {
        return new JWTAuthorizationFilter(this.excludedUrls);
    }
}
