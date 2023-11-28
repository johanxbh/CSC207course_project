package interface_adapter.post_plaza;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostPlazaViewModel extends ViewModel {
    public static final String PULL_LABEL = "refresh post plaza";
    public static final String CHECK_LIKED_POST_LABEL = "check liked post";
    public static final String NEW_POST_LABEL = "new post";
    public static final String COMMENT_LABEL = "comment";
    public static final String LIKE_LABEL = "like";
    private PostPlazaState state = new PostPlazaState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PostPlazaViewModel() {
        super("post plaza");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("post plaza new", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public PostPlazaState getState(){
        return this.state;
    }
    public void setState(PostPlazaState postPlazaState){
        this.state = postPlazaState;
    }


}
