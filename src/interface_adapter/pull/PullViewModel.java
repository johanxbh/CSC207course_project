package interface_adapter.pull;

import entities.postEntity;
import java.util.List;
import java.util.Observable;

public class PullViewModel extends Observable {
    private List<postEntity> latestPosts;

    public List<postEntity> getLatestPosts() {
        return latestPosts;
    }

    public void setLatestPosts(List<postEntity> latestPosts) {
        this.latestPosts = latestPosts;
        setChanged(); // Mark the view model as having been changed
    }
}
