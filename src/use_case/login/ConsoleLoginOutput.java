package use_case.login;

import entities.User;

public class ConsoleLoginOutput implements LoginOutputBoundary {
    @Override
    public void presentLoginSuccess(User user) {
        System.out.println("Login successful for user: " + user.getName());
    }

    @Override
    public void presentLoginFailure() {
        System.out.println("Login failed");
    }
}
