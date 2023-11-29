package app;

import data_access.FilePostAccessObject;
import entities.postEntity;
import interface_adapter.ViewManagerModel;
import interface_adapter.back.BackController;
import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentPresenter;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.list_liked_post.ListLikedPostController;
import interface_adapter.post_plaza.PostPlazaState;
import interface_adapter.post_plaza.PostPlazaViewModel;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInteracter;
import use_case.comment.CommentOutputBoundary;
import use_case.list_liked_post.ListLikedPostInputBoundary;
import use_case.list_liked_post.ListLikedPostInteractor;
import view.PostPlazaView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainTestForPostPlaza {
    public static void main(String[] args){
        JFrame application = new JFrame("PostPlaza");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        PostPlazaViewModel postPlazaViewModel = new PostPlazaViewModel();
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
        Iterator<postEntity> iter  =  listOfpostEntites.iterator();
        HashMap<Integer, postEntity> testHashMap = new HashMap<Integer, postEntity>();
        for (int i = 0; i <= 300; i++){
            postEntity onePost = iter.next();
            testHashMap.put(i, onePost);
        }
        PostPlazaState postPlazaState = new PostPlazaState();
        postPlazaState.setPostMap(testHashMap);
        postPlazaViewModel.setState(postPlazaState);
        postPlazaViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(postPlazaViewModel.getViewName());
        viewManagerModel.firePropertyChanged();




        ListLikedPostInputBoundary fakeinteractor = new ListLikedPostInteractor();
        ListLikedPostController fakecontroller = new ListLikedPostController(fakeinteractor);
        BackController fakebackcontroller = new BackController();

        CommentOutputBoundary fakecommentpresenter  = new CommentPresenter(new CommentViewModel("comment"));
        CommentInputBoundary fakecommentInteractor = new CommentInteracter(fakecommentpresenter, new FilePostAccessObject());
        PostPlazaView postPlazaView = new PostPlazaView(postPlazaViewModel, fakebackcontroller, new CommentController(fakecommentInteractor), postView);



        views.add(postPlazaView, postPlazaView.viewName);
        viewManagerModel.setActiveView(postPlazaView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);


    }
}
