package use_case.cancel;

public class cancelInteractor implements cancelInputBoundary{
    private cancelOutputBoundary cancelPresenter;
    public cancelInteractor(cancelOutputBoundary cancelPresenter){
        this.cancelPresenter = cancelPresenter;
    }
    @Override
    public void execute() {
        cancelPresenter.execute();
    }
}
