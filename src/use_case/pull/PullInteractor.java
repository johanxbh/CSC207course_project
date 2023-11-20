package use_case.pull;

import java.util.List;

import entities.postEntity;

public class PullInteractor implements PullInputBoundary {

    private final PullOutputBoundary output;
    private final PullUserDataAccessInterface userDataAccess;

    public PullInteractor(PullOutputBoundary output, PullUserDataAccessInterface userDataAccess) {
        this.output = output;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void refreshPosts() {
        List<postEntity> newPosts = userDataAccess.fetchNewestPosts();
        PullOutputData outputData = new PullOutputData(newPosts);
        output.presentNewPosts(outputData);
    }
}
