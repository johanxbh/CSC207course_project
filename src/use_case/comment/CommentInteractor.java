package use_case.comment;

import entities.postEntity;

public class CommentInteracter implements CommentInputBoundary{

    private CommentOutputBoundary commentOutputBoundary;


    public CommentInteracter(CommentOutputBoundary commentOutputBoundary) {
        this.commentOutputBoundary = commentOutputBoundary;
    }

    @Override
    public void execute(CommentInputData commentInputData) {
        String comment = commentInputData.getComment();
        postEntity post = commentInputData.getPost();
        post.getPostComment().add(comment);
        commentOutputBoundary.prepareSuccessView(new CommentOutputData(comment));
    }

    public void execute(postEntity post){
        commentOutputBoundary.prepareSuccessView(post);
    }
}
