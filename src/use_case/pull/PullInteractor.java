package use_case.pull;

import java.io.IOException;
import java.util.List;

import data_access.postDAO;
import entities.postEntity;

public class PullInteractor implements PullInputBoundary {

    private final PullOutputBoundary output;
    private final postDAO userDataAccess;

    public PullInteractor(PullOutputBoundary output, postDAO userDataAccess) {
        this.output = output;
        this.userDataAccess = (postDAO) userDataAccess;
    }

    @Override
    public void refreshPosts() throws IOException {
        List<postEntity> newPosts = userDataAccess.getLatestPosts();
        PullOutputData outputData = new PullOutputData(newPosts);
        output.presentNewPosts(outputData);
    }
}