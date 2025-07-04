package task_manager.testing.mapper;


import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import task_manager.testing.entity.CommentsEntity;
import task_manager.testing.entity.TaskEntity;
import task_manager.testing.model.CommentsDto;
import task_manager.testing.model.TaskDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TaskMapper {

    List<TaskDto> taskEntityToTaskDto(List<TaskEntity> listTaskEntities);


    TaskDto taskEntityToTaskDto (TaskEntity cardEntityInfo);

    TaskEntity taskDtoToTaskEntity(TaskDto taskDto);
    List<CommentsDto> commentsEntityToCommentsDto (List<CommentsEntity> commentsEntity);

//    @AfterMapping
//    default void afterTaskMapping(TaskEntity taskEntity, @MappingTarget TaskDto target) {
//
//        if (taskEntity.getCreationAT() != null) {
//            String maskedNumber = "**** **** **** " + cardEntityInfo.getCardNumber().substring(12);
//            target.setCardNumber(maskedNumber);
//        }
//
//    }

}
