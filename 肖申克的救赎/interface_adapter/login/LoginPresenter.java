package interface_adapter.login;

import entities.User;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import interface_adapter.ViewManagerModel;

import interface_adapter.post_plaza.PostPlazaViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.PostPlazaView;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final PostPlazaViewModel postPlazaViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel, PostPlazaViewModel postPlazaViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.postPlazaViewModel = postPlazaViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        LoginState logginState = loginViewModel.getState();
        this.loginViewModel.setState(logginState);
        this.loginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(postPlazaViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentLoginSuccess(User user) {
    }

    @Override
    public void presentLoginFailure() {

    }
}