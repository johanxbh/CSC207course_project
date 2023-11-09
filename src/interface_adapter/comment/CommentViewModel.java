package interface_adapter.comment;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CommentViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private CommentState state = new CommentState();

    public CommentViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setComment(String Comment) {
        this.state.setComment(Comment);
    }

    public String getComment() {
        return this.state.getComment();
    }
}
