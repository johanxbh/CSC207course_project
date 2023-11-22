package interface_adapter.list_liked_post;

import interface_adapter.ViewModel;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ListLikedPostViewModel extends ViewModel {
    private ListLikedPostState state = new ListLikedPostState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public ListLikedPostViewModel(String viewName) {
        super("liked post");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ListLikedPostState getState(){
        return state;
    }
    public void setState(ListLikedPostState newstate){
        this.state = newstate;
    }
}
