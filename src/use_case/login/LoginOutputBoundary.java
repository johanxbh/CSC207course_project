package use_case.login;

import entities.User;

public interface LoginOutputBoundary {
    void presentLoginSuccess(User user);
    void presentLoginFailure();
}

