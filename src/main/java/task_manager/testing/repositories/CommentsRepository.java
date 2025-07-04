package task_manager.testing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task_manager.testing.entity.CommentsEntity;
import task_manager.testing.entity.TaskEntity;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity,Long> {
    List<CommentsEntity> findByTask (TaskEntity task);
}
