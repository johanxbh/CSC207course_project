package use_case.comment;

import entities.postEntity;

public class CommentInputData {
    private postEntity post;
    final private String comment;

    public CommentInputData(postEntity post, String comment) {
        this.post = post;
        this.comment = comment;
    }

    public postEntity getPost() {
        return post;
    }

    public String getComment() {
        return comment;
    }
}

