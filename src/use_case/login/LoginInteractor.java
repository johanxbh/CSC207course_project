package use_case.login;

import app.generatePosts;  // Assuming GeneratePosts is in the 'app' package
import data_access.postDAO;
import entities.User;

public class LoginInteractor implements LoginInputBoundary {
    final postDAO userDataAccessObject;
    final LoginOutputBoundary loginOutputBoundary;

    public LoginInteractor(postDAO userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginOutputBoundary = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        // Implement the login logic here
        int username = loginInputData.getUsername();
        userDataAccessObject.saveUser(username);
        loginOutputBoundary.prepareSuccessView(new LoginOutputData(username));
    }
}