package use_case.login;

import entities.User;
public class LoginInputData {

    final private int username;

    public LoginInputData(int username) {
        this.username = username;
    }

    int getUsername() {
        return username;
    }
}
