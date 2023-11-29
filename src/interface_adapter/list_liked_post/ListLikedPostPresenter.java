package interface_adapter.list_liked_post;

import interface_adapter.ViewManagerModel;
import use_case.list_liked_post.ListLikedPostOutputBoundary;
import use_case.list_liked_post.ListLikedPostOutputData;
import entities.postEntity;
import java.util.ArrayList;

public class ListLikedPostPresenter implements ListLikedPostOutputBoundary {
    private final ListLikedPostViewModel likedPostViewModel;
    private ViewManagerModel viewManagerModel;


    public ListLikedPostPresenter(ListLikedPostViewModel likedPostViewModel, ViewManagerModel viewManagerModel) {
        this.likedPostViewModel = likedPostViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ListLikedPostOutputData listLikedPostOutputData) {
            ListLikedPostState listLikedPostState = this.likedPostViewModel.getState();
            ArrayList<postEntity> listOfPost = listLikedPostOutputData.getListOfPost();
            listLikedPostState.setListOfLikedPostArray(listOfPost);
            listLikedPostState.setHaveLikedPostState(true);
            this.likedPostViewModel.setState(listLikedPostState);
            this.likedPostViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(likedPostViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ListLikedPostState listLikedPostState = this.likedPostViewModel.getState();
        listLikedPostState.setHaveLikedPostState(false);
        listLikedPostState.setPostError(error);
        likedPostViewModel.firePropertyChanged();
    }
}
