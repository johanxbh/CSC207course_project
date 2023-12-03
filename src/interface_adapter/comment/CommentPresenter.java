package interface_adapter.comment;

import entities.postEntity;
import interface_adapter.ViewManagerModel;
import use_case.comment.CommentOutputBoundary;
import use_case.comment.CommentOutputData;

public class CommentPresenter implements CommentOutputBoundary {

    CommentViewModel commentViewModel;
    ViewManagerModel viewManagerModel;

    public CommentPresenter(ViewManagerModel viewManagerModel,
                            CommentViewModel commentViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.commentViewModel = commentViewModel;
    }

    @Override
    public void prepareSuccessView(CommentOutputData commentOutputData) {
        commentViewModel.getState().setComment(commentOutputData.getComment());
        viewManagerModel.setActiveView("plazaView");
        viewManagerModel.firePropertyChanged();
    }

    public void prepareSuccessView(postEntity post){
        commentViewModel.getState().setPost(post);
        viewManagerModel.setActiveView("commentView");
        viewManagerModel.firePropertyChanged();
    }
}
