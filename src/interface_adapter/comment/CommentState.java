package interface_adapter.comment;

import entities.postEntity;

public class CommentState {

    private postEntity post;
    private String Comment;

    public CommentState(postEntity post, String Comment) {
        this.post = post;
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

    public postEntity getPost() {
        return post;
    }

    public void setPost(postEntity post) {
        this.post = post;
    }
}
