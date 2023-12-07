package test;
import data_access.InMemoryDataAccessObject;
import entities.postEntity;
import org.junit.Before;
import org.junit.Test;
import use_case.post.PostinputData;
import use_case.post.postInteractor;
import use_case.post.postOutputBoundary;
import use_case.post.postOutputData;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class postInteractorTest {
    private InMemoryDataAccessObject successDataAccessObj;

    @Before
    public void init(){
        successDataAccessObj = new InMemoryDataAccessObject();
    }
    @Test
    public void testSuccessPost() throws IOException {
        postOutputBoundary successPresenter = new postOutputBoundary() {
            @Override
            public void prepareSuccessView(postOutputData data) {
                assertEquals(data.getPost().getPostPicture(), "This is also a TEST");
                assertEquals(data.getPost().getPosttext(), "This is a TEST");
                assertTrue(successDataAccessObj.getLatestPosts().contains(data.getPost()));
            }

            @Override
            public void prepareFailView() {
                fail("UseCase failed unexpectedly");

            }

            @Override
            public void prepareRefreshView(List<postEntity> latestPosts) {

            }
        };
        postInteractor successInteractor = new postInteractor(successPresenter, successDataAccessObj);
        PostinputData data = new PostinputData("This is a TEST", "This is also a TEST");
        successInteractor.execute(data);
    }
    @Test
    public void testFailPost() throws IOException {
        postOutputBoundary failPresenter = new postOutputBoundary() {
            @Override
            public void prepareSuccessView(postOutputData data) {
                fail("UseCase failed unexpectedly");
            }

            @Override
            public void prepareFailView() {
                assertTrue(true);
            }

            @Override
            public void prepareRefreshView(List<postEntity> latestPosts) {

            }
        };
        postInteractor successInteractor = new postInteractor(failPresenter, successDataAccessObj);
        PostinputData data = new PostinputData(null,null);
        successInteractor.execute(data);
    }

}
