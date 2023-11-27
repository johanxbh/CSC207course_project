package interface_adapter;
import entities.postEntity;
import use_case.post.postOutputBoundary;
import use_case.post.postOutputData;

import java.util.List;

public class postPresenter implements postOutputBoundary{
    @Override
    public void prepareSuccessView(postOutputData data) {

    }

    @Override
    public void prepareFailView() {

    }

    @Override
    public void prepareRefreshView(List<postEntity> latestPosts) {

    }
}
