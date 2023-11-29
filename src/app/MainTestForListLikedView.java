package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.back.BackController;
import interface_adapter.list_liked_post.ListLikedPostController;
import interface_adapter.list_liked_post.ListLikedPostPresenter;
import interface_adapter.list_liked_post.ListLikedPostState;
import interface_adapter.list_liked_post.ListLikedPostViewModel;
import use_case.list_liked_post.ListLikedPostInputBoundary;
import use_case.list_liked_post.ListLikedPostInteractor;
import use_case.list_liked_post.ListLikedPostOutputData;
import view.ListLikedPostView;
import view.ViewManager;
import entities.postEntity;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainTestForListLikedView {
    public static void main(String[] args){
        JFrame application = new JFrame("test LikedPost");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        ListLikedPostViewModel listLikedPostViewModel = new ListLikedPostViewModel();
        ListLikedPostPresenter listLikedPostPresenter = new ListLikedPostPresenter(listLikedPostViewModel, viewManagerModel);
        ArrayList<postEntity> listOfpostEntites = new ArrayList<postEntity>();
        for (int i = 0; i <= 100; i++ ){
            postEntity onePost = new postEntity("this is a post for test" + String.valueOf(i));
            onePost.updatePostComment("this is a test for comment" + String.valueOf(i));
            listOfpostEntites.add(onePost);
        }
        for (int i = 0; i <= 100; i++){
            postEntity onePost = new postEntity("this is a extra l" + "o".repeat(500)+ "ng post");
            listOfpostEntites.add(onePost);
        }
        for (int i = 0; i <= 100; i++){
            postEntity onePost = new postEntity("this is a extra l" + "o".repeat(1000)+ "ng post");
            listOfpostEntites.add(onePost);
        }
        ListLikedPostState listLikedPostState = new ListLikedPostState();
        ListLikedPostOutputData listLikedPostOutputData =  new ListLikedPostOutputData(listOfpostEntites);
        listLikedPostPresenter.prepareSuccessView(listLikedPostOutputData);
        ListLikedPostInputBoundary fakeinteractor = new ListLikedPostInteractor();
        ListLikedPostController fakecontroller = new ListLikedPostController(fakeinteractor);
        BackController fakebackcontroller = new BackController();
        ListLikedPostView listLikedPostView = new ListLikedPostView(listLikedPostViewModel, fakecontroller,fakebackcontroller);

        views.add(listLikedPostView, listLikedPostView.viewName);
        viewManagerModel.setActiveView(listLikedPostView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);


    }
}
