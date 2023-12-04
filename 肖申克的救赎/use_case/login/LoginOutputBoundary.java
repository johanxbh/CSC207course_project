package use_case.login;

import entities.User;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData response);

    void presentLoginSuccess(User user);
    void presentLoginFailure();
}

