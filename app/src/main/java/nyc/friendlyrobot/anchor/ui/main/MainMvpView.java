package nyc.friendlyrobot.anchor.ui.main;

import java.util.List;

import nyc.friendlyrobot.anchor.data.model.Loo;
import nyc.friendlyrobot.anchor.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showLoo(List<Loo> ribots);

    void showError();

}
