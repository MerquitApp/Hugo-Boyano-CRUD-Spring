package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.UserDetailsDTO;
import one.hgo.crudspring.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService userService;

    @Autowired
    private JWTService jwtService;

    public String login(UserDetailsDTO userDTO) {
        boolean isValid = this.userService.isValid(userDTO.getUsername(), userDTO.getPassword());

        if (!isValid) {
            return null;
        }

        if(userDTO.getPassword().isEmpty()) {
            return null;
        }

        UserDetails user = this.userService.getByUsername(userDTO.getUsername());
        return this.jwtService.generateToken(user.getId());
    }

    public void register(UserDetailsDTO userDTO) {
        this.userService.create(userDTO);
    }

    public UserDetails getUserByJwt(String token) {
        boolean isValid = this.jwtService.isValid(token);

        if (!isValid) {
            return null;
        }

        Long userId = this.jwtService.getJwtId(token);
        return this.userService.getById(userId);
    }
}
