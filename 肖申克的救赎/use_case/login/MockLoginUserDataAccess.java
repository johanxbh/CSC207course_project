package use_case.login;

import entities.User;

public class MockLoginUserDataAccess implements LoginUserDataAccessInterface {
    @Override
    public User getUser(int userId) {
        // In a real scenario, this method would query a database or another data source.
        // Here's a mock implementation for demonstration purposes.
        if (userId == 12345) {  // Example check
            return new User(userId);
        }
        return null;
    }
}
