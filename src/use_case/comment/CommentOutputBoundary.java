package use_case.comment;

import entities.postEntity;

public interface CommentOutputBoundary {
    void prepareSuccessView(CommentOutputData commentOutputData);

    void prepareSuccessView(postEntity post);
}
