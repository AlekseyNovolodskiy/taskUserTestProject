package task_manager.testing.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import task_manager.testing.model.TaskDto;
import task_manager.testing.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskUserController {

    private final TaskService taskService;

    @PostMapping("/create_task")
    public void createTask(@RequestBody TaskDto taskDto, Authentication jwtauth){
        taskService.createTask(taskDto, jwtauth.getName());
    }
    @PostMapping("/updateTask")
    public void updateTask(@RequestBody TaskDto taskDto, Authentication jwtauth){
        taskService.updateTask(taskDto,jwtauth.getName());
    }
    @PostMapping("/deleteTask")
    public void deleteTask(@RequestBody TaskDto taskDto, Authentication jwtauth){
        taskService.deleteTask(taskDto,jwtauth.getName());
    }
    @GetMapping("/showTasks")
    public  List<TaskDto> showTasks(Authentication jwtauth){
        return taskService.showAllTasks(jwtauth.getName());
    }
}
//Вот типикал тестовое
//Забыл вчера отправить
//
//ТЗ Java Ноябрь
//Разработка Системы Управления Задачами
//Описание задачи:
//
//Вам необходимо разработать простую систему управления задачами (Task Management System) с использованием Java, Spring.
//Система должна обеспечивать создание, редактирование, удаление и просмотр задач. Каждая задача должна содержать заголовок, описание, статус (например, "в ожидании", "в процессе", "завершено"), приоритет (например, "высокий", "средний", "низкий") и комментарии, а также автора задачи и исполнителя.
//Реализовать необходимо только API.
//
//Требования:
//
//Сервис должен поддерживать аутентификацию и авторизацию пользователей по email и паролю.
//Доступ к API должен быть аутентифицирован с помощью JWT токена.
//Создать ролевую систему администратора и пользователей.
//Администратор может управлять всеми задачами: создавать новые, редактировать существующие, просматривать и удалять, менять статус и приоритет, назначать исполнителей задачи, оставлять комментарии.
//Пользователи могут управлять своими задачами, если указаны как исполнитель: менять статус, оставлять комментарии.
//API должно позволять получать задачи конкретного автора или исполнителя, а также все комментарии к ним. Необходимо обеспечить фильтрацию и пагинацию вывода.
//Сервис должен корректно обрабатывать ошибки и возвращать понятные сообщения, а также валидировать входящие данные.
//Сервис должен быть хорошо задокументирован. API должен быть описан с помощью Open API и Swagger. В сервисе должен быть настроен Swagger UI. Необходимо написать README с инструкциями для локального запуска проекта. Дев среду нужно поднимать с помощью docker compose.
//Напишите несколько базовых тестов для проверки основных функций вашей системы.
//Используйте для реализации системы язык Java 17+, Spring, Spring Boot. В качестве БД можно использовать PostgreSQL или MySQL. Для реализации аутентификации и авторизации нужно использовать Spring Security. Можно использовать дополнительные инструменты, если в этом есть необходимость (например кэш).
//Оценка: Оцениваться будут следующие аспекты
//
//Соответствие требованиям.
//Качество и чистота кода.
//Проектирование системы и использование ООП.
//Наличие тестов и их покрытие.
//Обработка ошибок.