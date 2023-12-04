package app;

import data_access.postDataAccessObject;
import entities.postEntity;

import java.io.IOException;
import java.util.ArrayList;

public class MainTestForPostDAO {
    public static void main(String[] args) throws IOException {
        postDataAccessObject postDataAccessObject = new postDataAccessObject("sl.BrDOxbbL_hv_qPowuHhNFNBxYtI59yG-kGmwtuR011-ENqBuiOzOGTWjPM6d4aZ2QaYlcy-BAvASU-_PMTdGYpcZkQQedZodc6lIw03oCCMuCIIhLnzufw2NzbEBmbWZHY-HAAMkvZvmDN_PeAFyJmQ");
        postEntity postEntity = new postEntity("son of a bitch", "/home/johan/Pictures/123.png");
//        postDataAccessObject.downloadFile("/APPENDING_FILE.txt", "APPENDING_FILE_test.txt");
        postDataAccessObject.savePost(postEntity);
//        postEntity newPost = postDataAccessObject.getPost(5);
//        System.out.println("post1 post picture:"+newPost.getPostPicture());
        ArrayList<postEntity> testGetLastestPosts = (ArrayList<postEntity>) postDataAccessObject.getLatestPosts();
//        System.out.println(postDataAccessObject.checkFileExist("/POST/15.png"));
        System.out.println(testGetLastestPosts.size());
    }
}
