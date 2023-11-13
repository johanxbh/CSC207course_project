package interface_adapter.comment;

import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;

public class CommentController {
    CommentInputBoundary commentInteracter;

    public CommentController(CommentInputBoundary commentInteracter) {
        this.commentInteracter = commentInteracter;
    }

    public void excute(String comment) {
        CommentInputData commentInputData = new CommentInputData(comment);
        commentInteracter.execute(commentInputData);
    }
}
