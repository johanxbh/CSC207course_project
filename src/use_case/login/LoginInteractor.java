package use_case.login;

import app.generatePosts;  // Assuming GeneratePosts is in the 'app' package

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginOutputBoundary;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginOutputBoundary = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        // Implement the login logic here
        int username = loginInputData.getUsername();
        // Example: Validate the username, authenticate, etc.
        // If login is successful, call the API
        generatePosts postGenerator = new generatePosts();
        try {
            String fakePost = postGenerator.getFakePost();
            System.out.println("Fetched post: " + fakePost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
