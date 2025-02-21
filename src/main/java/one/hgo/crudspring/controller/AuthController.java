package one.hgo.crudspring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import one.hgo.crudspring.dto.UserDetailsDTO;
import one.hgo.crudspring.model.UserDetails;
import one.hgo.crudspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/register/form")
    public String registerForm(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Cookie cookie = WebUtils.getCookie(request, "auth");
        UserDetails user = null;

        if (cookie != null) {
            user = this.authService.getUserByJwt(cookie.getValue());
        }

        if (user != null) {
            response.sendRedirect("/proyectos/ver");
        }

        return "register";
    }

    @GetMapping("/login/form")
    public String loginForm(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Cookie cookie = WebUtils.getCookie(request, "auth");
        UserDetails user = null;

        if (cookie != null) {
            user = this.authService.getUserByJwt(cookie.getValue());
        }

        if (user != null) {
            response.sendRedirect("/proyectos/ver");
        }

        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDetailsDTO userDTO) {
        this.authService.register(userDTO);
        return ResponseEntity.ok("Usuario Registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDetailsDTO userDTO, HttpServletResponse response) {
        String token = this.authService.login(userDTO);

        if (token == null) {
            return ResponseEntity.notFound().build();
        }

        Cookie cookie = new Cookie("auth", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("Usuario Logado");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("auth", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }

    @GetMapping("/login/github")
    public String loginWithGithub(HttpServletRequest request) {
        return "redirect:/oauth2/authorization/github"; // Redirige a la URL que Spring Security espera
    }
}
