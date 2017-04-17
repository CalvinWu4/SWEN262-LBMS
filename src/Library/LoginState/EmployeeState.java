package Library.LoginState;

/**
 * Created by Calvin on 4/11/2017.
 */
public class EmployeeState implements LoginState{
    @Override
    public void setContext(LoginContext context) {
        context.setState(this);
    }
}
