package use_case.pull;

public class PullInteractor implements PullInputBoundary {


    private PullDataAccessInterface dataAccess;
    private PullOutputBoundary outputBoundary;

    public PullInteractor(PullDataAccessInterface dataAccess, PullOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

}
