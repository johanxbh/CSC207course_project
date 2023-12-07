package interface_adapter.comment;

import entities.postEntity;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;

public class CommentController {
    CommentInputBoundary commentInteracter;

    public CommentController(CommentInputBoundary commentInteracter) {
        this.commentInteracter = commentInteracter;
    }

    public void execute(postEntity post, String comment) {
        CommentInputData commentInputData = new CommentInputData(post, comment);
        commentInteracter.execute(commentInputData);
    }

    public void execute(postEntity post){
        commentInteracter.execute(post);
    }
}
