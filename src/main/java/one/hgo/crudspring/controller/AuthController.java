package one.hgo.crudspring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import one.hgo.crudspring.dto.UsersDTO;
import one.hgo.crudspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsersDTO userDTO) {
        this.authService.register(userDTO);
        return ResponseEntity.ok("Usuario Registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersDTO userDTO, HttpServletResponse response) {
        String token = this.authService.login(userDTO);

        if (token == null) {
            return ResponseEntity.notFound().build();
        }

        Cookie cookie = new Cookie("auth", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok("Usuario Logado");
    }
}
