package test;
import data_access.InMemoryDataAccessObject;
import entities.postEntity;
import org.junit.Before;
import org.junit.Test;
import use_case.cancel.cancelInteractor;
import use_case.cancel.cancelOutputBoundary;
import use_case.post.PostinputData;
import use_case.post.postInteractor;
import use_case.post.postOutputBoundary;
import use_case.post.postOutputData;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class cancelTest {
    @Test
    public void testSuccessCancel(){
        cancelOutputBoundary cancelpresenter = new cancelOutputBoundary() {
            @Override
            public void execute() {
                assert(true);
            }
        };
        cancelInteractor cancelInteractor = new cancelInteractor(cancelpresenter);
        cancelInteractor.execute();
    }


}
