package uk.co.ribot.androidboilerplate.util;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.CountingIdlingResource;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Espresso Idling resource that handles waiting for RxJava Observables executions.
 * This class must be used with RxIdlingExecutionHook.
 * Before registering this idling resource you must:
 * 1. Create an instance of this class.
 * 2. Register RxEspressoScheduleHandler with the RxJavaPlugins using setScheduleHandler()
 * 3. Register this idle resource with Espresso using Espresso.registerIdlingResources()
 */
public class RxEspressoScheduleHandler implements Function<Runnable, Runnable> {

    private final CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource("rxJava");

    @Override
    public Runnable apply(@NonNull final Runnable runnable) throws Exception {
        return new Runnable() {
            @Override
            public void run() {
                mCountingIdlingResource.increment();

                try {
                    runnable.run();
                } finally {
                    mCountingIdlingResource.decrement();
                }
            }
        };
    }

    public IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

}
