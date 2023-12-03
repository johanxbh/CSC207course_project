package interface_adapter.pull;

import use_case.pull.PullOutputBoundary;
import use_case.pull.PullOutputData;
import interface_adapter.pull.PullViewModel;

public class PullPresenter implements PullOutputBoundary {
    private final PullViewModel viewModel;

    public PullPresenter(PullViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentNewPosts(PullOutputData outputData) {
        viewModel.setLatestPosts(outputData.getPosts());
        viewModel.notifyObservers(); // Notify the view that the data has been updated
    }
}
