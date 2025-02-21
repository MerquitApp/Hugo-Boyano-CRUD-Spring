package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.UserDetailsDTO;
import one.hgo.crudspring.model.UserDetails;
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

    public UserDetails create(UserDetailsDTO userDTO) {
        UserDetails user = new UserDetails();
        String encodedPassword = null;

        if(userDTO.getPassword() != null) {
            encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        }

        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setPassword(encodedPassword);
        user.setGithubId(userDTO.getGithubId());

        return this.userRepository.save(user);
    }

    public UserDetails findOrCreateUser(UserDetailsDTO userDTO) {
        UserDetails user = this.userRepository.findUserByUsername(userDTO.getUsername().toLowerCase());

        if(user == null) {
            user = this.create(userDTO);
        }

        return user;
    }

    public UserDetails findByGithubId(String githubId) {
        return this.userRepository.findUserByGithubId(githubId);
    }

    public UserDetails getByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public boolean isValid(String username, String password) {
        UserDetails user = this.userRepository.findUserByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public UserDetails getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
