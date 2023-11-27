package interface_adapter.like;

import interface_adapter.ViewModel;
import interface_adapter.comment.CommentState;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LikeViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private LikeState state = new LikeState();

    public LikeViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LikeState getState() {
        return state;
    }

    public void setState(LikeState state) {
        this.state = state;
    }
}
