package ma.xproce.bshop.dao.repositories;

import ma.xproce.bshop.dao.entities.UserM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserM,Integer> {
    Optional<UserM> findByUsername(String username);
}