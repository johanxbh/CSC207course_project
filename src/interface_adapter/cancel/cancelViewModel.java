package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class cancelViewModel extends ViewModel{
    public static final String CANCEL_BUTTON_LABEL = "cancel";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public cancelViewModel(){
        super("cancel");
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("cancel",null,null);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
