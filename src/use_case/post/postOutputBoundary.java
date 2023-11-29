package use_case.post;

import entities.postEntity;

import java.util.List;

public interface postOutputBoundary {
    void prepareSuccessView(postOutputData data);
    void prepareFailView();

    void prepareRefreshView(List<postEntity> latestPosts);
}
