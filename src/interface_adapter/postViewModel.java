package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class postViewModel extends ViewModel{
    public static final String POST_BUTTON_LABEL = "Post";
    private postState postState = new postState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public postViewModel() {
        super("post");
    }

    public static interface_adapter.postState getState() {
        return null;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("post", null, this.postState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
