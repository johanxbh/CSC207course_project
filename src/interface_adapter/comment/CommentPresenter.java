package interface_adapter.comment;

import use_case.comment.CommentOutputBoundary;
import use_case.comment.CommentOutputData;

public class CommentPresenter implements CommentOutputBoundary {

    CommentViewModel commentViewModel;

    public CommentPresenter(CommentViewModel commentViewModel) {
        this.commentViewModel = commentViewModel;
    }

    @Override
    public void prepareSuccessView(CommentOutputData commentOutputData) {
        commentViewModel.getState().setComment(commentOutputData.getComment());
    }
}
