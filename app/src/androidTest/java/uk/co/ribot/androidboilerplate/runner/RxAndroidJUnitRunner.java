package uk.co.ribot.androidboilerplate.runner;

import android.os.Bundle;
import android.support.test.espresso.Espresso;

import uk.co.ribot.androidboilerplate.util.RxIdlingResource;

/**
 * Runner that registers a Espresso Indling resource that handles waiting for
 * RxJava Observables to finish.
 * WARNING - Using this runner will block the tests if the application uses long-lived hot
 * Observables such us event buses, etc.
 */
public class RxAndroidJUnitRunner extends UnlockDeviceAndroidJUnitRunner {

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        Espresso.registerIdlingResources(rxIdlingResource);
    }

}
