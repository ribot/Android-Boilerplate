package nyc.friendlyrobot.sample.ui.main;

import java.util.List;

import nyc.friendlyrobot.sample.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Object> ribots);

    void showRibotsEmpty();

    void showError();

}
