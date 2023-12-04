package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;
    private final JButton logIn;
    private final ViewManagerModel viewManagerModel;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.viewManagerModel = viewManagerModel;

        // Initialize the buttons
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        // Set up the button panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align the buttons in the center
        buttons.add(logIn); // Add the login button to the buttons panel
        loginViewModel.addPropertyChangeListener(this);
        // Set the layout for the LoginView panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Add components to the LoginView panel
        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // Add some space between the title and buttons
        this.add(buttons); // Add the buttons panel to the LoginView panel
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                loginController.execute(random.nextInt(10000));
            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("login state")){
            viewManagerModel.setActiveView("post plaza");
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}