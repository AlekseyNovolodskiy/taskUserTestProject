package task_manager.testing.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import task_manager.testing.model.TaskDto;
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

    @PostMapping(CREATE_TASK)
    public void createTask(@RequestBody TaskDto taskDto, Authentication jwtauth){
        taskService.createTask(taskDto, jwtauth.getName());
    }
    @PostMapping(UPDATE_TASK)
    public void updateTask(@RequestBody TaskDto taskDto, Authentication jwtauth){
        taskService.updateTask(taskDto,jwtauth.getName());
    }
    @PostMapping(DELETE_TASK)
    public void deleteTask(@RequestBody TaskDto taskDto, Authentication jwtauth){
        taskService.deleteTask(taskDto,jwtauth.getName());
    }
    @GetMapping(SHOW_TASK)
    public  List<TaskDto> showTasks(Authentication jwtauth){
        return taskService.showAllTasks(jwtauth.getName());
    }
}
