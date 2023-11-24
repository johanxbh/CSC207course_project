package interface_adapter.list_liked_post;

import use_case.list_liked_post.ListLikedPostOutputBoundary;
import use_case.list_liked_post.ListLikedPostOutputData;
import entities.postEntity;
import java.util.ArrayList;

public class ListLikedPostPresenter implements ListLikedPostOutputBoundary {
    private final ListLikedPostViewModel likedPostViewModel;


    public ListLikedPostPresenter(ListLikedPostViewModel likedPostViewModel) {
        this.likedPostViewModel = likedPostViewModel;
    }

    @Override
    public void prepareSuccessView(ListLikedPostOutputData listLikedPostOutputData) {
            ListLikedPostState listLikedPostState = this.likedPostViewModel.getState();
            ArrayList<postEntity> listOfPost = listLikedPostOutputData.getListOfPost();
            listLikedPostState.setListOfLikedPostArray(listOfPost);
            this.likedPostViewModel.setState(listLikedPostState);
            this.likedPostViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {

    }
}
