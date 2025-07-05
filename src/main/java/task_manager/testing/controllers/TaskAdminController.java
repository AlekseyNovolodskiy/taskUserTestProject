package task_manager.testing.controllers;

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
@Tag(name = "Управление аккаунтом для пользователей со статусом 'Администратор'", description = "Api для управления аккаунтом с доступом ADMIN")
public class TaskAdminController {

    private final TaskAdminService taskService;

    @PostMapping(CREATE_ADMIN_TASK)
    public TaskDto createTask(@RequestBody TaskDto taskDto, @RequestParam Integer userId){
       return taskService.createTaskForUser(taskDto, userId);
    }
    @PostMapping(UPDATE_ADMIN_TASK)
    public TaskDto updateTask(@RequestBody TaskDto taskDto, @RequestParam Integer userId){
        return taskService.updateUserTask(taskDto,userId);
    }
    @PostMapping(DELETE_ADMIN_TASK)
    public void deleteTask(@RequestBody TaskDto taskDto, @RequestParam Integer userId){
        taskService.deleteUserTask(taskDto,userId);
    }
    @GetMapping(SHOW_ADMIN_TASK)
    public List<TaskDto> showTasks(){
        return taskService.showAllTask();
    }
    @GetMapping(LEAVE_ADMIN_COMMENTS)
    public void leaveAdminComments (@RequestParam String taskName, @RequestParam String comment, Authentication jwtauth){
        taskService.leaveAdminTasksComments(taskName,comment,jwtauth.getName());
    }

}
