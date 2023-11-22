package interface_adapter.list_liked_post;

import use_case.list_liked_post.ListLikedPostOutputBoundary;
import use_case.list_liked_post.ListLikedPostOutputData;

public class ListLikedPostPresenter implements ListLikedPostOutputBoundary {
    private final ListLikedPostViewModel likedPostViewModel;


    public ListLikedPostPresenter(ListLikedPostViewModel likedPostViewModel) {
        this.likedPostViewModel = likedPostViewModel;
    }

    @Override
    public void prepareSuccessView(ListLikedPostOutputData listLikedPostOutputData) {

    }

    @Override
    public void prepareFailView() {

    }
}
