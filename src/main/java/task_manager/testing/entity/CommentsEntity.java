package task_manager.testing.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments_info")
public class CommentsEntity {
    @Id
    @SequenceGenerator(name = "comments_infoSequence", sequenceName = "comments_info_sequence", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name="task_id", nullable=false)
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;
}
