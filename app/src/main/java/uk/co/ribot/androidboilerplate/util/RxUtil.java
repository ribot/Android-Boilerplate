package uk.co.ribot.androidboilerplate.util;

import io.reactivex.disposables.Disposable;
import rx.Subscription;

public class RxUtil {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
