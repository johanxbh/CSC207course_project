package data_access;
import entities.postEntity;
import java.util.ArrayList;

public interface postDAO {
    public ArrayList<postEntity>getMostRecentPost();
    public void savePost(postEntity post);
    public void cleanAllPost();
}
