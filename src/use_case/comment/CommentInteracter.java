package use_case.comment;

public class CommentInteracter implements CommentInputBoundary{

    private CommentOutputBoundary commentOutputBoundary;

    private CommentUserDataAccessInterface commentUserDataAccessInterface;

    public CommentInteracter(CommentOutputBoundary commentOutputBoundary, CommentUserDataAccessInterface commentUserDataAccessInterface) {
        this.commentOutputBoundary = commentOutputBoundary;
        this.commentUserDataAccessInterface = commentUserDataAccessInterface;
    }

    @Override
    public void execute(CommentInputData commentInputData) {
        String comment = commentInputData.getComment();
        commentUserDataAccessInterface.saveComment(comment);
        commentOutputBoundary.prepareSuccessView(new CommentOutputData(comment));
    }
}
