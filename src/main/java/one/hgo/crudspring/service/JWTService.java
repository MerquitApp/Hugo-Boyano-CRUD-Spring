package one.hgo.crudspring.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Long id) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withJWTId(id.toString())
                .sign(algorithm);
    }
}
