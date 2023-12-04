import entities.User;
import entities.postEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    private postEntity postEntity;
    private User user;
    @Before
    public void init(){
        user = new User(123);
        postEntity = new postEntity("this is a test","this is another test");
    }
    @Test
    public void testCheckLikedPost(){
        user.addLikedPost(postEntity);
        assertTrue(user.checkedLikedPost(postEntity));
    }
    @Test
    public void testGetId(){
        assertEquals(user.getName(), 123);
    }
    @Test
    public void testLikedPost(){
        postEntity.setPostID(0);
        user.addLikedPost(postEntity);
        ArrayList<postEntity> newList = new ArrayList<>();
        newList.add(postEntity);
        assertEquals(user.getLikedPost().get(0).toString(), "0");
    }
}
