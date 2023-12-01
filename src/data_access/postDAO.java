package data_access;
import entities.postEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface postDAO {
    public postEntity getMostRecentPost() throws IOException;
    public void savePost(postEntity post);
    public void cleanAllPost();
    public postEntity getMostPopularPost();
    public postEntity getPost(Integer postid);
    public List<postEntity> getLatestPosts();

    postEntity getlatestPost(List<postEntity> posts);
}
