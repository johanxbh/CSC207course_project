package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import use_case.login.*;
import view.LoginView;
import view.ViewManager;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Implementations for LoginUserDataAccessInterface and LoginOutputBoundary
        LoginUserDataAccessInterface userDataAccess = new MockLoginUserDataAccess();
        LoginOutputBoundary loginOutput = new ConsoleLoginOutput();

        LoginInputBoundary loginInputBoundary = new LoginInteractor(userDataAccess, loginOutput);
        LoginController loginController = new LoginController(loginInputBoundary);


        LoginViewModel loginViewModel = new LoginViewModel();
        LoginView loginView = new LoginView(loginViewModel, loginController);

        views.add(loginView, loginView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}