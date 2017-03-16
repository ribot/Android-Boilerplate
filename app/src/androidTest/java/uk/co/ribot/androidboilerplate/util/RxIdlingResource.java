package uk.co.ribot.androidboilerplate.util;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;
import timber.log.Timber;

/**
 * Espresso Idling resource that handles waiting for RxJava Observables executions.
 * This class must be used with RxIdlingExecutionHook.
 * Before registering this idling resource you must:
 * 1. Create an instance of RxIdlingExecutionHook by passing an instance of this class.
 * 2. Register RxIdlingExecutionHook with the RxJavaPlugins using registerObservableExecutionHook()
 * 3. Register this idle resource with Espresso using Espresso.registerIdlingResources()
 */
public class RxIdlingResource implements IdlingResource {

    private final AtomicInteger mActiveSubscriptionsCount = new AtomicInteger(0);
    private ResourceCallback mResourceCallback;

    public RxIdlingResource() {
        setupHooks();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return mActiveSubscriptionsCount.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }

    private void setupHooks() {
        RxJavaHooks.setOnObservableStart(new Func2<Observable, OnSubscribe, OnSubscribe>() {
            @Override
            public OnSubscribe call(Observable observable, OnSubscribe onSubscribe) {
                incrementActiveSubscriptionsCount();
                return onSubscribe;
            }
        });

        RxJavaHooks.setOnObservableSubscribeError(new Func1<Throwable, Throwable>() {
            @Override
            public Throwable call(Throwable throwable) {
                decrementActiveSubscriptionsCount();
                return throwable;
            }
        });

        RxJavaHooks.setOnObservableReturn(new Func1<Subscription, Subscription>() {
            @Override
            public Subscription call(Subscription subscription) {
                decrementActiveSubscriptionsCount();
                return subscription;
            }
        });
    }

    private void incrementActiveSubscriptionsCount() {
        int count = mActiveSubscriptionsCount.incrementAndGet();
        Timber.i("Active subscriptions count increased to %d", count);
    }

    private void decrementActiveSubscriptionsCount() {
        int count = mActiveSubscriptionsCount.decrementAndGet();
        Timber.i("Active subscriptions count decreased to %d", count);
        if (isIdleNow()) {
            Timber.i("There is no active subscriptions, transitioning to Idle");
            mResourceCallback.onTransitionToIdle();
        }
    }

}
