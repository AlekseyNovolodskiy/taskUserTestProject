package task_manager.testing.exeption;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CardManagerExceptionHandler {

    @ExceptionHandler(UserException.class)
    public String catchUserExeption(UserException u){
        return u.getMessage();
    }


    @ExceptionHandler(TaskException.class)
    public String catchTaskExeption(TaskException t){
        return t.getMessage();
    }
}
