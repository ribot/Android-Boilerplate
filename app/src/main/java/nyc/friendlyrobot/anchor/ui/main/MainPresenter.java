package nyc.friendlyrobot.anchor.ui.main;

import javax.inject.Inject;

import nyc.friendlyrobot.anchor.data.model.Foo;
import nyc.friendlyrobot.anchor.data.store.FooStore;
import nyc.friendlyrobot.anchor.ui.base.BasePresenter;
import nyc.friendlyrobot.anchor.util.FriendlyScheduler;
import rx.Subscriber;
import rx.Subscription;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private final FooStore fooStore;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(FooStore store) {
        this.fooStore = store;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadFoos() {
        checkViewAttached();
        fooStore.get("a foo")
                .compose(FriendlyScheduler.schedule())
                .subscribe(new Subscriber<Foo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Foo foo) {
                        getMvpView().showLoo(foo.looList());
                    }
                });
    }

}
