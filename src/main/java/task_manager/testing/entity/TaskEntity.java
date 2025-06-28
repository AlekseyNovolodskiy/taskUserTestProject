package task_manager.testing.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;
}
