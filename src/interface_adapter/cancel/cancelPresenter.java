package interface_adapter.cancel;

import use_case.cancel.cancelOutputBoundary;

public class cancelPresenter implements cancelOutputBoundary {
    private cancelViewModel cancelViewModel;
    public cancelPresenter(cancelViewModel cancelViewModel){
        this.cancelViewModel = cancelViewModel;
    }
    @Override
    public void execute() {
        cancelViewModel.firePropertyChanged();
    }
}
