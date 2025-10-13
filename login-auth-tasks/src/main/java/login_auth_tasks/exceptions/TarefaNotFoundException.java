package login_auth_tasks.exceptions;

public class TarefaNotFoundException extends RuntimeException {
    
    public TarefaNotFoundException(String message){
        super(message);
    }
}
