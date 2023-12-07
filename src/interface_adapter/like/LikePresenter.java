package interface_adapter.like;

import interface_adapter.comment.CommentViewModel;
import use_case.comment.CommentOutputData;
import use_case.like.LikeOutputData;
import use_case.like.LikeOutputDataBoundary;

public class LikePresenter implements LikeOutputDataBoundary {
    LikeViewModel likeViewModel;

    public LikePresenter(LikeViewModel likeViewModel) {
        this.likeViewModel = likeViewModel;
    }

    @Override
    public void prepareSuccessView(LikeOutputData likeOutputData) {
        likeViewModel.getState().setLikeCount(likeOutputData.getLikeCount());
        likeViewModel.getState().setLike(Boolean.TRUE);
        likeViewModel.firePropertyChanged();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        likeViewModel.getState().setLike(Boolean.FALSE);
        likeViewModel.firePropertyChanged();
    }
}
