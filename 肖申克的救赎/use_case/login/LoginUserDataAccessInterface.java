package use_case.login;

import entities.User;

public interface LoginUserDataAccessInterface {
    User getUser(int userId);
}

