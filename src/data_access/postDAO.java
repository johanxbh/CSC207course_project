package data_access;
import entities.postEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface postDAO {
    public postEntity getMostRecentPost() throws IOException;
    public void savePost(postEntity post) throws IOException;
    public void cleanAllPost();
    public postEntity getMostPopularPost();
    public postEntity getPost(Integer postid) throws IOException;
    public List<postEntity> getLatestPosts() throws IOException;

    postEntity getlatestPost(List<postEntity> posts) throws IOException;
}
