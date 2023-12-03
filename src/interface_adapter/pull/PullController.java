package interface_adapter.pull;

import use_case.pull.PullInputBoundary;

public class PullController {
    private final PullInputBoundary pullInteractor;

    public PullController(PullInputBoundary pullInteractor) {
        this.pullInteractor = pullInteractor;
    }

    public void refreshPosts() {
        pullInteractor.refreshPosts();
    }
}