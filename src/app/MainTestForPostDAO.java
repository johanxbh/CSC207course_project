package app;

import data_access.postDataAccessObject;
import entities.postEntity;

import java.io.IOException;

public class MainTestForPostDAO {
    public static void main(String[] args) throws IOException {
        postDataAccessObject postDataAccessObject = new postDataAccessObject("sl.Bq8AuONESNVu98K-gKsIjgDT1V5a2uf9G799RfvdDzFOg4D2njN6TomN8-6rg_W6hALlv4-nWLNE-EhLj6FSqJuDHikpPi06uc1TqLekn-A5A0PcB1GddPwfNwR6k4V2-_ktfJ2-FLWmVB7x9o1MaWE");
//        postEntity postEntity = new postEntity("son of a bitch", "/home/johan/Pictures/123.png");
//        postDataAccessObject.downloadFile("/APPENDING_FILE.txt", "APPENDING_FILE_test.txt");
//        postDataAccessObject.savePost(postEntity);
        postEntity newPost = postDataAccessObject.getPost(15);
        System.out.println("post1 post picture:"+newPost.getPostPicture());
//        System.out.println(postDataAccessObject.checkFileExist("/POST/15.png"));
    }
}
