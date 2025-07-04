package task_manager.testing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import task_manager.testing.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    Optional<TaskEntity> findByTaskName(String taskName);

    @Query(value = "select t from TaskEntity t where t.taskName = :taskName")
    TaskEntity findTaskEntitiesWithOutOptional(String taskName);

    @Query(value = "select t from TaskEntity t where t.user.id = :userId")
    List<TaskEntity> findTaskListByUserId (Long userId);
}
