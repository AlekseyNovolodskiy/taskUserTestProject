package task_manager.testing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task_manager.testing.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntitiesByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}
