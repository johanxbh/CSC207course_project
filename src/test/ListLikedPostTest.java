package test;

import data_access.InMemoryDataAccessObject;
import entities.User;
import entities.postEntity;
import interface_adapter.list_liked_post.ListLikedPostInputData;
import org.junit.Before;
import org.junit.Test;
import use_case.list_liked_post.ListLikedPostInputBoundary;
import use_case.list_liked_post.ListLikedPostInteractor;
import use_case.list_liked_post.ListLikedPostOutputBoundary;
import use_case.list_liked_post.ListLikedPostOutputData;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ListLikedPostTest {
    private InMemoryDataAccessObject successDataAccessObject;

    @Before
    public void init(){
        successDataAccessObject = new InMemoryDataAccessObject();

    }
    @Test
    public void testSuccessView() throws IOException {
        ListLikedPostOutputBoundary ListLikedPostPresenter = new ListLikedPostOutputBoundary() {
            @Override
            public void prepareSuccessView(ListLikedPostOutputData listLikedPostOutputData) {
                assertTrue(listLikedPostOutputData.getListOfPost() instanceof List<postEntity>);
                System.out.println("output data size"+listLikedPostOutputData.getListOfPost().size());
                System.out.println(listLikedPostOutputData.getListOfPost().get(0));
                assertEquals(listLikedPostOutputData.getListOfPost().get(0).getPostPicture(), "this is a test for picture");
                assertEquals(listLikedPostOutputData.getListOfPost().get(0).getPostInfo(), "this is a test for postInfo");
                assertEquals(listLikedPostOutputData.getListOfPost().get(0).getPostComment().get(0), "this is a test for comment");


            }

            @Override
            public void prepareFailView(String s) {
                fail("this usecase should NOT fail");
            }

        };
        ListLikedPostInputBoundary listLikedPostInteractor = new ListLikedPostInteractor(successDataAccessObject,ListLikedPostPresenter);
        User user = new User(123);
        postEntity newPostEntity = new postEntity("this is a test for postInfo","this is a test for picture" );
        newPostEntity.setPostID(1);
        newPostEntity.updatePostComment("this is a test for comment");
        user.addLikedPost(newPostEntity);
        successDataAccessObject.savePost(newPostEntity);
        System.out.println(successDataAccessObject.getPost(1));
        ListLikedPostInputData listLikedPostInputData = new ListLikedPostInputData(user);
        listLikedPostInteractor.execute(listLikedPostInputData);
    }
    @Test
    public void testFailView() throws IOException {
        ListLikedPostOutputBoundary listLikedPostPresenter = new ListLikedPostOutputBoundary() {
            @Override
            public void prepareSuccessView(ListLikedPostOutputData listLikedPostOutputData) {
                fail("user did not like any post");
            }

            @Override
            public void prepareFailView(String s) {
                assertTrue(true);
            }
        };
        ListLikedPostInputBoundary listLikedPostInteractor = new ListLikedPostInteractor(successDataAccessObject, listLikedPostPresenter);
        User user = new User(123);
        ListLikedPostInputData listLikedPostInputData = new ListLikedPostInputData(user);
        listLikedPostInteractor.execute(listLikedPostInputData);
    }
}