package login_auth_tasks.exceptions;

public class UserNotFound extends RuntimeException {
    
    public UserNotFound(String message){
        super(message);
    }
}
