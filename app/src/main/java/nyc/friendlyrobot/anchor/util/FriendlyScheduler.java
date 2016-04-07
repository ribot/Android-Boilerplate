package nyc.friendlyrobot.anchor.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendlyScheduler<T> implements Observable.Transformer<T, T> {

    private FriendlyScheduler() {
    }

    public static <T> FriendlyScheduler<T> schedule() {
        return new FriendlyScheduler<T>();
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
