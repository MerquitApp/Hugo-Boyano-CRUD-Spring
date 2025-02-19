package one.hgo.crudspring.repository;

import one.hgo.crudspring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUserByUsername(String username);
}
