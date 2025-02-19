package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.UsersDTO;
import one.hgo.crudspring.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService userService;

    @Autowired
    private JWTService jwtService;

    public String login(UsersDTO userDTO) {
        boolean isValid = this.userService.isValid(userDTO.getUsername(), userDTO.getPassword());

        if (!isValid) {
            return null;
        }

        Users user = this.userService.getByUsername(userDTO.getUsername());
        return this.jwtService.generateToken(user.getId());
    }

    public void register(UsersDTO userDTO) {
        this.userService.create(userDTO);
    }
}
