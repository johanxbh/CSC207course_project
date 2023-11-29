import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LoginViewTest {

    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private LoginController mockLoginController;

    private LoginView loginView;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginView = new LoginView(mockLoginViewModel, mockLoginController);
    }

    @Test
    public void testInitialisation() {
        // Verify all components are initialised
        assertNotNull(loginView.usernameInputField);
        assertNotNull(loginView.passwordInputField);
        assertNotNull(loginView.logIn);
        assertNotNull(loginView.cancel);
    }

    @Test
    public void testLoginButtonClick() {
        // Simulate button click
        loginView.logIn.doClick();

        // Verify loginController.execute() is called
        verify(mockLoginController, times(1)).execute(anyString());
    }

    @Test
    public void testUserInputHandling() {
        // Simulate user input
        loginView.usernameInputField.setText("testUser");

        // Verify LoginViewModel state is updated
        verify(mockLoginViewModel, atLeastOnce()).setState(any(LoginState.class));
    }

    @Test
    public void testViewUpdateOnPropertyChange() {
        // Create a sample LoginState and trigger propertyChange
        LoginState sampleState = new LoginState("testUser", "testPassword");
        loginView.propertyChange(new PropertyChangeEvent(this, "state", null, sampleState));

        // Verify view is updated according to the new state
        // Depending on how setFields() is implemented
    }
}
