package postTest;
import entities.postEntity;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class postEntityTest {
    private postEntity post;
    @Before
    public void init(){
        post = new postEntity("Hello World!", "This is a TEST");
    }
    @Test
    public void testUpdatePostComments(){
        String newComment = "new comment";
        String newComment2 = "new comment2";
        post.updatePostComment(newComment);
        post.updatePostComment(newComment2);
        assertEquals(post.getPostComment().get(0), newComment);
        assertEquals(post.getPostComment().size(),2);
        assertEquals(post.getPostComment().get(1), newComment2);
    }
    @Test
    public void testSetPostLiked(){
        Integer newlike = 5;
        post.setPostLiked(newlike);
        assertEquals(post.getPostLiked(), newlike);
    }
    @Test
    public void testGetPosttext(){assertEquals(post.getPosttext(), "Hello World!");
    }
    @Test
    public void testGetPostComment(){
        assertEquals(post.getPostComment().size(),0);
    }
    @Test
    public void testGetPostPicture(){assertEquals(post.getPostPicture(), "This is a TEST");
    }

}
