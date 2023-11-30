package app;

import data_access.InMemoryDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.cancelViewModel;
import interface_adapter.postViewModel;
import view.ViewManager;
import javax.swing.*;
import java.awt.*;

import view.postView;
import data_access.postDAO;
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
        cancelViewModel cancelViewModel = new cancelViewModel();
        postDAO InMemoryDataAccessObject = new InMemoryDataAccessObject();
        postView postView = postViewFactory.create(viewManagerModel,InMemoryDataAccessObject,postViewModel, cancelViewModel);
        views.add(postView, postView.viewName);
        viewManagerModel.setActiveView(postView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
    }
}
