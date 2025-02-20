package one.hgo.crudspring.config;

import one.hgo.crudspring.filter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final List<AntPathRequestMatcher> excludedUrls = List.of(
            new AntPathRequestMatcher("/static/**"),
            new AntPathRequestMatcher("/auth/login"),
            new AntPathRequestMatcher("/auth/register")
    );

    private final List<AntPathRequestMatcher> publicUrls = List.of(
            new AntPathRequestMatcher("/auth/login/form"),
            new AntPathRequestMatcher("/auth/register/form")
    );

    private final List<AntPathRequestMatcher> privateUrls = List.of(
            new AntPathRequestMatcher("/proyectos/crear"),
            new AntPathRequestMatcher("/tareas/crear")
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                        .requestMatchers(Stream.concat(this.excludedUrls.stream(), this.publicUrls.stream()).toArray(AntPathRequestMatcher[]::new)).permitAll()
//                        .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterAfter(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter() {
        return new JWTAuthorizationFilter(this.publicUrls, this.privateUrls, this.excludedUrls);
    }
}
