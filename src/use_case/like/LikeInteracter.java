package use_case.like;

import data_access.postDAO;
import entities.User;
import entities.postEntity;
import use_case.comment.CommentUserDataAccessInterface;

public class LikeInteracter implements LikeInputBoundary{
    private LikeOutputDataBoundary likeOutputDataBoundary;


    public LikeInteracter(LikeOutputDataBoundary likeOutputDataBoundary) {
        this.likeOutputDataBoundary = likeOutputDataBoundary;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        postEntity newPostEntity = likeInputData.getLikePost();
        newPostEntity.setPostLiked(1);
        User user = likeInputData.getUser();
        if (! user.checkedLikedPost(newPostEntity)){
        likeInputData.getUser().addLikedPost(likeInputData.getLikePost());
        }

        likeOutputDataBoundary.prepareSuccessView(new LikeOutputData(1));
    }
}