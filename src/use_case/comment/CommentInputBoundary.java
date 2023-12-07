package use_case.comment;

import entities.postEntity;

public interface CommentInputBoundary {
    void execute(CommentInputData commentInputData);

    void execute(postEntity post);
}
