package interface_adapter.cancel;

import use_case.cancel.cancelInputBoundary;

public class cancelController {
    private cancelInputBoundary cancelInteractor;
    public cancelController(cancelInputBoundary cancelInteractor){
        this.cancelInteractor = cancelInteractor;
    }
    public void execute(){
        cancelInteractor.execute();
    }
}
