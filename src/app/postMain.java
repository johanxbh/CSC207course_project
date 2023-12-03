package app;

import data_access.InMemoryDataAccessObject;
import data_access.postDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.cancelViewModel;
import interface_adapter.post.postViewModel;
import view.ViewManager;
import view.postView;

import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Post");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        postViewModel postViewModel = new postViewModel();
        postDAO InMemoryDataAccessObject = new InMemoryDataAccessObject();
        cancelViewModel cancelViewModel = new cancelViewModel();
        postView postView = postViewFactory.create(viewManagerModel,InMemoryDataAccessObject,postViewModel, cancelViewModel);
        views.add(postView, postView.viewName);
        viewManagerModel.setActiveView(postView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
    }
}
