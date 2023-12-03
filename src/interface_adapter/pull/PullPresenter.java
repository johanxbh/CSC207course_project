package interface_adapter.pull;

import interface_adapter.post_plaza.PostPlazaState;
import use_case.pull.PullOutputBoundary;
import use_case.pull.PullOutputData;
import interface_adapter.post_plaza.PostPlazaViewModel;

public class PullPresenter implements PullOutputBoundary {
    private final PullViewModel viewModel;

    private final PostPlazaViewModel postPlazaViewModel;

    public PullPresenter(PullViewModel viewModel, PostPlazaViewModel postPlazaViewModel) {
        this.viewModel = viewModel;
        this.postPlazaViewModel = postPlazaViewModel;
    }

    @Override
    public void presentNewPosts(PullOutputData outputData) {
        viewModel.setLatestPosts(outputData.getPosts());
        PostPlazaState state = postPlazaViewModel.getState();
        state.setPostList(outputData.getPosts());
        state.setNeedUpdate(true);
        postPlazaViewModel.firePropertyChanged();
        viewModel.notifyObservers(); // Notify the view that the data has been updated
    }
}
