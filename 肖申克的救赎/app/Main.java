package app;

import data_access.postDAO;
import data_access.postDataAccessObject;
import entities.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.back.BackController;
import interface_adapter.cancel.cancelViewModel;
import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentPresenter;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.like.LikeController;
import interface_adapter.like.LikePresenter;
import interface_adapter.like.LikeViewModel;
import interface_adapter.list_liked_post.ListLikedPostController;
import interface_adapter.list_liked_post.ListLikedPostPresenter;
import interface_adapter.list_liked_post.ListLikedPostViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.post.postViewModel;
import interface_adapter.post_plaza.PostPlazaViewModel;
import interface_adapter.pull.PullController;
import interface_adapter.pull.PullPresenter;
import interface_adapter.pull.PullViewModel;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInteracter;
import use_case.comment.CommentOutputBoundary;
import use_case.like.LikeInputBoundary;
import use_case.like.LikeInteracter;
import use_case.like.LikeOutputDataBoundary;
import use_case.list_liked_post.ListLikedPostInputBoundary;
import use_case.list_liked_post.ListLikedPostInteractor;
import use_case.list_liked_post.ListLikedPostOutputBoundary;
import use_case.login.*;
import use_case.pull.PullInputBoundary;
import use_case.pull.PullInteractor;
import use_case.pull.PullOutputBoundary;
import view.*;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        JFrame application = new JFrame("anonymous post");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        PostPlazaViewModel postPlazaViewModel = new PostPlazaViewModel();
        LoginUserDataAccessInterface userDataAccess = new MockLoginUserDataAccess();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginOutputBoundary loginOutput = new LoginPresenter(viewManagerModel, loginViewModel, postPlazaViewModel);
        postViewModel postViewModel = new postViewModel();
        postDAO postDataAccessObject = new postDataAccessObject("sl.BrG3fUQ7YFoo3UzmrfNDqVqH-6J4fRSrRtLnmPv28gYEi9PqJdt-FkzGkuwvqz-Hh7VTu_E0ddbeUvR8-FKAUONMLdTfvc9Kl8JMsfC9YMIG5pjPjdHbB3rebUuPLOohJNzGmHDSoAZIcBClq4Y83Ho");

        LoginInputBoundary loginInputBoundary = new LoginInteractor(postDataAccessObject, loginOutput);
        LoginController loginController = new LoginController(loginInputBoundary);
        cancelViewModel cancelViewModel = new cancelViewModel();
        postView postView = postViewFactory.create(viewManagerModel,postDataAccessObject,postViewModel, cancelViewModel);
        BackController backController = new BackController();
        CommentViewModel commentViewModel = new CommentViewModel("commentView");
        CommentOutputBoundary commentPresenter = new CommentPresenter(viewManagerModel, commentViewModel);
        CommentInputBoundary commentInteractor = new CommentInteracter(commentPresenter);
        CommentController commentController = new CommentController(commentInteractor);
        CommentView commentView = new CommentView(commentViewModel,commentController);
        LikeViewModel likeViewModel = new LikeViewModel("likeViewModel");
        LikeOutputDataBoundary likePresenter = new LikePresenter(likeViewModel);
        LikeInputBoundary likeInteractor = new LikeInteracter(likePresenter);
        LikeController likeController = new LikeController(likeInteractor);
        ListLikedPostViewModel listLikedPostViewModel = new ListLikedPostViewModel();
        ListLikedPostOutputBoundary listLikedPostPresenter = new ListLikedPostPresenter(listLikedPostViewModel, viewManagerModel, postPlazaViewModel);
        ListLikedPostInputBoundary listLikedPostInteractor = new ListLikedPostInteractor(postDataAccessObject,listLikedPostPresenter);
        ListLikedPostController listLikedPostController = new ListLikedPostController(listLikedPostInteractor);

        PullViewModel pullViewModel = new PullViewModel();
        PullOutputBoundary pullPresenter = new PullPresenter(pullViewModel, postPlazaViewModel);
        PullInputBoundary pullInteractor = new PullInteractor(pullPresenter,postDataAccessObject);
        PullController pullController = new PullController(pullInteractor);

        ListLikedPostView listLikedPostView = new ListLikedPostView(listLikedPostViewModel, listLikedPostController);
        PostPlazaView postPlazaView = new PostPlazaView(commentViewModel, postPlazaViewModel,viewManagerModel,listLikedPostViewModel,postViewModel, likeController, listLikedPostController, pullController, postView, commentView, listLikedPostView, new User(123));
        views.add(postPlazaView, postPlazaView.viewName);
        LoginView loginView = new LoginView(loginViewModel, loginController, viewManagerModel, postPlazaView);
        views.add(loginView, loginView.viewName);
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
    }
}