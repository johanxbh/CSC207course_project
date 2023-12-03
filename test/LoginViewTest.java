import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;
import view.LoginView;
import use_case.login.LoginInteractor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LoginViewTest {

    private LoginViewModel mockViewModel;
    private LoginController mockController;
    private LoginView loginView;

    @Before
    public void setUp() {
        mockViewModel = new LoginViewModel(); // Assuming you have a default constructor
        mockController = new LoginController(); // Assuming you have a default constructor
        loginView = new LoginView(mockViewModel, mockController);
    }

    @Test
    public void testInitialization() {
        assertNotNull(loginView.logIn);
        assertNotNull(loginView.cancel);
        // Add more assertions to check initial state of the view
    }

    @Test
    public void testActionPerformed() {
        // You can simulate an action event and call the actionPerformed method
        ActionEvent testEvent = new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "Test");
        loginView.actionPerformed(testEvent);

        // Check if the method behaved as expected
        // Since actionPerformed just prints to console in your case, this might be challenging to test without further logic
    }

    @Test
    public void testTextFieldInput() {
        loginView.usernameInputField.setText("testUser");
        assertEquals("testUser", loginView.usernameInputField.getText());
    }
}