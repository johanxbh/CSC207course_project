package interface_adapter.post;

import interface_adapter.ViewModel;
import interface_adapter.post.postState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class postViewModel extends ViewModel {
    public static final String POST_BUTTON_LABEL = "post";
    public static final String PICTURE_BUTTON_LABEL = "selectPicture";
    public static final String LABLE_TITLE = "postview";
    public static final String GENERATE_POST_LABEL = "generatePost";
    private interface_adapter.post.postState postState = new postState();
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
    public postState getState(){
        return postState;
    }
    public void setPostState(postState state){
        this.postState = state;
    }
}
