package use_case.login;

public class LoginInputData {

    final private int username;

    public LoginInputData(int username) {
        this.username = username;
    }

    int getUsername() {
        return username;
    }
}
