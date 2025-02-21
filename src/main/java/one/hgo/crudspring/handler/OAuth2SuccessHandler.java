package one.hgo.crudspring.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import one.hgo.crudspring.model.UserDetails;
import one.hgo.crudspring.service.JWTService;
import one.hgo.crudspring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsersService usersService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
            String githubId = Integer.toString(oauthUser.getAttribute("id"));

            UserDetails user = this.usersService.findByGithubId(githubId);
            String token = this.jwtService.generateToken(user.getId());

            Cookie cookie = new Cookie("auth", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            cookie.setPath("/");
            response.addCookie(cookie);

            response.sendRedirect("/proyectos/ver");
        }
    }
}
