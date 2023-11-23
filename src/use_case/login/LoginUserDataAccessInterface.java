package use_case.login;

import entities.User;

public interface LoginUserDataAccessInterface {
    User get (int Username);
}
