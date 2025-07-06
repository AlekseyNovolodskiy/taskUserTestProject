package task_manager.testing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import task_manager.testing.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntitiesByEmail(String email);
    boolean existsById(Long id);
    @Query(value = "select u from UserEntity u where u.nameOfUser = :userName")
    Optional<UserEntity> findByUserName (String userName);

}
