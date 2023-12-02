package view;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    final JButton logIn;
    final JButton cancel;

    /**
     * A window with a title and a JButton.
     */
    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(evt -> {
            LoginState state = (LoginState) evt.getNewValue();
            // You can handle any state changes here if needed
        });

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == logIn) {
            // Handle login logic here
            System.out.println("Login button clicked");
        } else if (evt.getSource() == cancel) {
            // Handle cancel logic here
            System.out.println("Cancel button clicked");
        }
    }

}
