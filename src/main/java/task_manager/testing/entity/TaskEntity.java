package task_manager.testing.entity;

import jakarta.persistence.*;
import lombok.Data;
import task_manager.testing.model.TaskPriority;
import task_manager.testing.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "task_info")
public class TaskEntity {
    @Id
    @SequenceGenerator(name = "task_infoSequence", sequenceName = "task_info_sequence", allocationSize = 1, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private String taskName;
    private String taskDescription;
    private LocalDateTime creationAT;
    private LocalDateTime expiredAT;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentsEntity> comments;
}
