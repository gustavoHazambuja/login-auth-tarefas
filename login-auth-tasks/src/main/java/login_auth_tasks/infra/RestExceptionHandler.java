package login_auth_tasks.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import login_auth_tasks.exceptions.TarefaNotFoundExcepton;
import login_auth_tasks.exceptions.UserNotFound;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    

    @ExceptionHandler(TarefaNotFoundExcepton.class)
    private ResponseEntity<String> tarefaNotFound(TarefaNotFoundExcepton exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    private ResponseEntity<String> userNotFound(UserNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
