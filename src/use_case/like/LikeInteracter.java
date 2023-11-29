package use_case.like;

import use_case.comment.CommentUserDataAccessInterface;

public class LikeInteracter implements LikeInputBoundary{
    private LikeOutputDataBoundary likeOutputDataBoundary;

    private CommentUserDataAccessInterface commentUserDataAccessInterface;

    public LikeInteracter(LikeOutputDataBoundary likeOutputDataBoundary) {
        this.likeOutputDataBoundary = likeOutputDataBoundary;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        int likeCount = likeInputData.getLikePost().getPostLiked();
        likeInputData.getLikePost().setPostLiked(++likeCount);
        likeOutputDataBoundary.prepareSuccessView(new LikeOutputData(likeCount));
    }
}
