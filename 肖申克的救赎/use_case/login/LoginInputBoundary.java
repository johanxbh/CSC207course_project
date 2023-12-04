package use_case.login;

import entities.User;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);
}
