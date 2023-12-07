package interface_adapter.like;

import entities.postEntity;
import use_case.like.LikeInputBoundary;
import use_case.like.LikeInputData;

public class LikeController {
    LikeInputBoundary likeInteracter;

    public LikeController(LikeInputBoundary likeInteracter) {
        this.likeInteracter = likeInteracter;
    }

    public void execute(postEntity post) {
        LikeInputData likeInputData = new LikeInputData(post);
        likeInteracter.execute(likeInputData);
    }
}
