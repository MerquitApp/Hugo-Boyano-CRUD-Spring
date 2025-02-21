package one.hgo.crudspring.repository;

import one.hgo.crudspring.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findUserByUsername(String username);
    UserDetails findUserByGithubId(String githubId);
}
