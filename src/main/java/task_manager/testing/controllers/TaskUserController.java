package task_manager.testing.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import task_manager.testing.model.CommentsDto;

import task_manager.testing.model.TaskDto;
import task_manager.testing.model.TaskUserDto;
import task_manager.testing.service.TaskUserService;

import java.util.List;

import static task_manager.testing.controllers.ControllerConstant.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(TASK_CONTROLLER_HEAD)
@Tag(name = "Управление аккаунтом для пользователей со статусом 'Пользователь'", description = "Api для управления аккаунтом с доступом USER")
public class TaskUserController {

    private final TaskUserService taskService;

    @PostMapping(UPDATE_TASK)
    public void updateTask(@RequestBody TaskUserDto taskDto, Authentication jwtauth){
        taskService.updateTask(taskDto,jwtauth.getName());
    }
    @Operation(summary = "просмотр всех своих задач")
    @GetMapping(SHOW_TASK)
    public  List<TaskDto> showTasks(Authentication jwtauth){
        return taskService.showAllTasks(jwtauth.getName());
    }
    @Operation(summary = "просмотр всех комментариев")
    @Parameter(name = "имя задачи", description = "имя задачи, коментарии, которой мы хотим просмотреть", example = "task2")
    @GetMapping(SHOW_COMMENTS)
    public List<CommentsDto> showComments (@RequestParam String taskName, Authentication jwtauth){
       return taskService.showTasksComments(taskName,jwtauth.getName());
    }

    @Operation(summary = "оставляем комментарий к своему проекту")
    @Parameter(name = "имя задачи", description = "имя задачи, где мы хотим оставить коментарий", example = "task2")
    @Parameter(name = "коментарий", description = "оставляем сам коментарий", example = "все сделал,я молодец")
    @GetMapping(LEAVE_COMMENTS)
    public void leaveComments (@RequestParam String taskName, @RequestParam String comment, Authentication jwtauth){
        taskService.leaveTasksComments(taskName,comment,jwtauth.getName());
    }
}
