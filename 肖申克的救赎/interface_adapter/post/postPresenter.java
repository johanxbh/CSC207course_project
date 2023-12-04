package interface_adapter.post;
import entities.postEntity;
import interface_adapter.ViewManagerModel;
import use_case.post.postOutputBoundary;
import use_case.post.postOutputData;

import java.util.List;

public class postPresenter implements postOutputBoundary{
    private interface_adapter.post.postViewModel postViewModel;
    private ViewManagerModel viewManagerModel;
    public postPresenter(postViewModel postViewModel, ViewManagerModel viewManagerModel){
        this.postViewModel = postViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(postOutputData data) {
        postViewModel.getState().setPostText(data.getPost().getPosttext());
        postViewModel.getState().setPostPictureText(data.getPost().getPostPicture());
        postViewModel.getState().setPostSuccess("true");
        postViewModel.firePropertyChanged();
        System.out.println("from presenter" + postViewModel.getState().getPostPictureText());
        postViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(postViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView() {
        postViewModel.getState().setPostError("please post something");
        postViewModel.getState().setPostSuccess("false");
        postViewModel.firePropertyChanged();
    }

    @Override
    public void prepareRefreshView(List<postEntity> latestPosts) {

    }
}
