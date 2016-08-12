package com.incendiary.androidboilerplate.ui.main;

import java.util.List;

import com.incendiary.androidboilerplate.data.model.Ribot;
import com.incendiary.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
