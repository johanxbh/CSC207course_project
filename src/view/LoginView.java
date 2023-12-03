package view;

import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoginView extends JPanel implements ActionListener {
    public static final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;
    final JButton logIn;
    final JButton cancel;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        // Initialize the buttons
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);

        // Set up the button panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align the buttons in the center
        buttons.add(logIn); // Add the login button to the buttons panel
        buttons.add(cancel); // Add the cancel button to the buttons panel

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        // Set the layout for the LoginView panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add components to the LoginView panel
        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // Add some space between the title and buttons
        this.add(buttons); // Add the buttons panel to the LoginView panel
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == logIn) {
            int username = new Random().nextInt(Integer.MAX_VALUE);
            loginController.execute(username);
        } else if (evt.getSource() == cancel) {
            System.out.println("Cancel button clicked");
        }
    }
}
