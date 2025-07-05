package task_manager.testing.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import task_manager.testing.model.TaskDto;
import task_manager.testing.service.TaskAdminService;

import java.util.List;

import static task_manager.testing.controllers.ControllerConstant.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping(TASK_CONTROLLER_HEAD)
@Tag(name = "Управление задачами для пользователей со статусом 'Администратор'", description = "Api для управления аккаунтом с доступом ADMIN")
public class TaskAdminController {

    private final TaskAdminService taskService;

    @PostMapping(CREATE_ADMIN_TASK)
    public TaskDto createTask(@RequestBody TaskDto taskDto){
       return taskService.createTaskForUser(taskDto);
    }
    @PostMapping(UPDATE_ADMIN_TASK)
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateUserTask(taskDto);
    }
    @PostMapping(DELETE_ADMIN_TASK)
    public void deleteTask(@RequestBody TaskDto taskDto){
        taskService.deleteUserTask(taskDto);
    }

    @Operation(summary = "просмотр всех задач")
    @GetMapping(SHOW_ADMIN_TASK)
    public List<TaskDto> showTasks(){
        return taskService.showAllTask();
    }

    @Operation(summary = "просмотр всех комментариев")
    @Parameter(name = "имя задачи", description = "имя задачи, где мы хотим оставить коментарий", example = "task2")
    @Parameter(name = "коментарий", description = "оставляем сам коментарий", example = "Хорошая работа, но надо переделать")
    @GetMapping(LEAVE_ADMIN_COMMENTS)
    public void leaveAdminComments (@RequestParam String taskName, @RequestParam String comment, Authentication jwtauth){
        taskService.leaveAdminTasksComments(taskName,comment,jwtauth.getName());
    }

}
