package interface_adapter.login;

import interface_adapter.postViewModel;
import interface_adapter.postState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the post plaza in view.

        postState loggedInState = postViewModel.getState();
        loggedInState.setUsername(response.getUsername());;
        this.viewManagerModel.firePropertyChanged();
    }
}
