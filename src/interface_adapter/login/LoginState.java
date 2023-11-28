package interface_adapter.login;

public class LoginState {
    private int username;


    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    public int getUsername() {
        return username;
    }
}
