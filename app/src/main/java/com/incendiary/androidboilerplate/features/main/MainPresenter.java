package com.incendiary.androidboilerplate.features.main;

import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import com.incendiary.androidboilerplate.data.DataManager;
import com.incendiary.androidboilerplate.data.model.Ribot;
import com.incendiary.androidboilerplate.di.ConfigPersistent;
import com.incendiary.androidboilerplate.features.common.BasePresenter;
import com.incendiary.androidboilerplate.util.RxUtil;

@ConfigPersistent public class MainPresenter extends BasePresenter<MainMvpView> {

  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject public MainPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MainMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void loadRibots() {
    checkViewAttached();
    RxUtil.unsubscribe(mSubscription);
    mSubscription = mDataManager.getRibots()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<List<Ribot>>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Timber.e(e, "There was an error loading the ribots.");
            getMvpView().showError();
          }

          @Override public void onNext(List<Ribot> ribots) {
            if (ribots.isEmpty()) {
              getMvpView().showRibotsEmpty();
            } else {
              getMvpView().showRibots(ribots);
            }
          }
        });
  }
}
