package interface_adapter.comment;

public class CommentState {

    private String Comment;

    public CommentState(String Comment) {
        this.Comment = Comment;
    }

    public CommentState() {
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
}
