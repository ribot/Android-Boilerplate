package com.incendiary.androidboilerplate.features.main;

import java.util.List;

import com.incendiary.androidboilerplate.data.model.Ribot;
import com.incendiary.androidboilerplate.features.common.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
