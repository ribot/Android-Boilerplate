package uk.co.ribot.androidboilerplate.util;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

/**
 * Provides helper methods to post event to an Otto event bus
 */
public class EventPosterHelper {

    private final RxEventBus mRxEventBus;

    @Inject
    public EventPosterHelper(RxEventBus bus) {
        mRxEventBus = bus;
    }

    public RxEventBus getBus() {
        return mRxEventBus;
    }

    /**
     * Helper method to post an event from a different thread to the main one.
     */
    public void postEventSafely(final Object event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mRxEventBus.post(event);
            }
        });
    }
}
