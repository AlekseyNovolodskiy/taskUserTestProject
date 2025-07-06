package task_manager.testing.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import task_manager.testing.entity.TaskEntity;
import task_manager.testing.entity.UserEntity;
import task_manager.testing.exeption.TaskException;
import task_manager.testing.exeption.UserException;
import task_manager.testing.model.TaskUserDto;
import task_manager.testing.repositories.TaskRepository;
import task_manager.testing.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static task_manager.testing.service.implementation.util.TaskUserUtilTest.*;

@ExtendWith(MockitoExtension.class)
class TaskUserServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private TaskUserServiceImpl taskUserService;

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        UserEntity userExecutor = getUserEntity();
        TaskUserDto taskUserDto = getTaskUserDto();

        String email = userExecutor.getEmail();
        when(taskRepository.findByTaskName(taskUserDto.getTaskName()))
                .thenReturn(Optional.empty());
        TaskException exception = assertThrows(
                TaskException.class,
                () -> taskUserService.updateTask(taskUserDto, email)
        );

        String expectedMessage = "Задача с таким именем не существует";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        verify(taskRepository).findByTaskName(taskUserDto.getTaskName());
        verifyNoMoreInteractions(taskRepository);
    }
    @Test
    void shouldThrowExceptionWhenUserNotFound(){
        UserEntity userExecutor = getUserEntity();
        TaskEntity newTask = getTaskEntity(userExecutor);
        TaskUserDto taskUserDto = getTaskUserDto();

        String email = userExecutor.getEmail();

        when(taskRepository.findByTaskName(taskUserDto.getTaskName()))
                .thenReturn(Optional.of(newTask));
        when(userRepository.findUserEntitiesByEmail(email)).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class,
                ()->taskUserService.updateTask(taskUserDto,email));
        String expectedMessage = "Пользователь не найден";
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));

        verify(taskRepository).findByTaskName(taskUserDto.getTaskName());
        verify(userRepository).findUserEntitiesByEmail(email);
        verifyNoMoreInteractions(userRepository,taskRepository);
    }
    @Test
    void shouldThrowExceptionWhenUserHasNoAccess(){
        UserEntity userExecutor = getUserEntity();
        TaskEntity newTask = getTaskEntity(userExecutor);
        TaskUserDto taskUserDto = getTaskUserDto();
        UserEntity wrongExecutor = getWrongUserEntity();

        String email = userExecutor.getEmail();

        when(taskRepository.findByTaskName(taskUserDto.getTaskName()))
                .thenReturn(Optional.of(newTask));
        when(userRepository.findUserEntitiesByEmail(email)).thenReturn(Optional.of(wrongExecutor));

        UserException exception = assertThrows(UserException.class,
                ()->taskUserService.updateTask(taskUserDto,email));

        String expectedMessage = "Вы не имеете доступа к данной задаче";
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    void positiveCase(){
        UserEntity userExecutor = getUserEntity();
        TaskEntity newTask = getTaskEntity(userExecutor);
        TaskUserDto taskUserDto = getTaskUserDto();

        String email = userExecutor.getEmail();

        when(taskRepository.findByTaskName(taskUserDto.getTaskName()))
                .thenReturn(Optional.of(newTask));
        when(userRepository.findUserEntitiesByEmail(email)).thenReturn(Optional.of(userExecutor));
        assertDoesNotThrow(() -> taskUserService.updateTask(taskUserDto, email));
        assertEquals(taskUserDto.getStatus(),newTask.getStatus());
    }
}
