package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.UsersDTO;
import one.hgo.crudspring.model.Users;
import one.hgo.crudspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void create(UsersDTO userDTO) {
        Users user = new Users();
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
    }

    public Users getByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public boolean isValid(String username, String password) {
        Users user = this.userRepository.findUserByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
