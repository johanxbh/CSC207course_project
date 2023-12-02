package view;

import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoginView extends JPanel implements ActionListener {
    public static final String viewName = "log in";  // viewName declared as a public static variable
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;
    final JButton logIn;
    final JButton cancel;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
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
