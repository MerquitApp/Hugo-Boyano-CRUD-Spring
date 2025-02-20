package one.hgo.crudspring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import one.hgo.crudspring.model.Users;
import one.hgo.crudspring.service.AuthService;
import one.hgo.crudspring.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthService authService;

    private final List<AntPathRequestMatcher> publicUrls;
    private final List<AntPathRequestMatcher> privateUrls;
    private final List<AntPathRequestMatcher> excludedUrls;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = WebUtils.getCookie(request, "auth");
        boolean isPublicUrl = this.publicUrls.stream().anyMatch(matcher -> matcher.matches(request));
        boolean isPrivateUrl = this.privateUrls.stream().anyMatch(matcher -> matcher.matches(request));
        Users user = null;

        if (cookie != null) {
            user = this.authService.getUserByJwt(cookie.getValue());
        }

        boolean isAuthenticated = user != null;

        if (isAuthenticated && isPublicUrl) {
            response.sendRedirect("/proyectos/crear");
        } else if (!isAuthenticated && isPrivateUrl) {
            response.sendRedirect("/auth/login/form");
        } else if (isAuthenticated) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, null);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return this.excludedUrls.stream().anyMatch(matcher -> matcher.matches(request));
    }
}
