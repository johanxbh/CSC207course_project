package data_access;
import entities.postEntity;
import java.util.ArrayList;

public interface postDAO {
    public postEntity getMostRecentPost();
    public void savePost(postEntity post);
    public void cleanAllPost();
    public postEntity getMostPopularPost();
    public postEntity getPost(Integer postid);
}
