package uk.co.ribot.androidboilerplate.util;

import rx.Observable;
import rx.Subscription;
import rx.plugins.RxJavaObservableExecutionHook;

/**
 * RxJava Observable execution hook that handles updating the active subscription
 * count for a given Espresso RxIdlingResource.
 */
public class RxIdlingExecutionHook extends RxJavaObservableExecutionHook {

    private RxIdlingResource mRxIdlingResource;

    public RxIdlingExecutionHook(RxIdlingResource rxIdlingResource) {
        mRxIdlingResource = rxIdlingResource;
    }

    @Override
    public <T> Observable.OnSubscribe<T> onSubscribeStart(
            Observable<? extends T> observableInstance, Observable.OnSubscribe<T> onSubscribe) {
        mRxIdlingResource.incrementActiveSubscriptionsCount();
        return super.onSubscribeStart(observableInstance, onSubscribe);
    }

    @Override
    public <T> Throwable onSubscribeError(Throwable e) {
        mRxIdlingResource.decrementActiveSubscriptionsCount();
        return super.onSubscribeError(e);
    }

    @Override
    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        mRxIdlingResource.decrementActiveSubscriptionsCount();
        return super.onSubscribeReturn(subscription);
    }
}
