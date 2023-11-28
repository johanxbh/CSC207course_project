package interface_adapter;

import data_access.postState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class postViewModel extends ViewModel{
    public static final String POST_BUTTON_LABEL = "Post";
    private postState postState = new postState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public postViewModel() {
        super("post");
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
