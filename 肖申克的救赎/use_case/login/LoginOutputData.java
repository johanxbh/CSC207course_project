package use_case.login;

public class LoginOutputData {

    private final int username;

    public LoginOutputData(int username) {
        this.username = username;
    }

    public int getUsername() {
        return username;
    }

}
