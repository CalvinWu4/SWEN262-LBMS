package Library.LoginState;

/**
 * Created by Calvin on 4/11/2017.
 */
public class LoginContext {
    private LoginState state;

    public LoginContext(){
        state = new LoggedOutState();
    }

    public void setState(LoginState state){
        this.state = state;
    }

    public LoginState getState(){
        return state;
    }

}
