package interface_adapter.like;

import entities.postEntity;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;
import use_case.like.LikeInputBoundary;
import use_case.like.LikeInputData;

public class LikeController {
    LikeInputBoundary likeInteracter;

    public LikeController(LikeInputBoundary likeInteracter) {
        this.likeInteracter = likeInteracter;
    }

    public void excute(postEntity postEntity) {
        LikeInputData likeInputData = new LikeInputData(postEntity);
        likeInteracter.execute(likeInputData);
    }
}
