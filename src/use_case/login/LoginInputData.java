package use_case.login;

public class LoginInputData {
    private final int username;

    public LoginInputData(int username) {
        this.username = username;
    }

    public int getUsername() {
        return username;
    }
}