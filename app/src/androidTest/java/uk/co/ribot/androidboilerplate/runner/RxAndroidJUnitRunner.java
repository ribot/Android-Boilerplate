package uk.co.ribot.androidboilerplate.runner;

import android.os.Bundle;
import android.support.test.espresso.Espresso;

import io.reactivex.plugins.RxJavaPlugins;
import uk.co.ribot.androidboilerplate.util.RxEspressoScheduleHandler;

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

        RxEspressoScheduleHandler rxEspressoScheduleHandler = new RxEspressoScheduleHandler();
        RxJavaPlugins.setScheduleHandler(rxEspressoScheduleHandler);
        Espresso.registerIdlingResources(rxEspressoScheduleHandler.getIdlingResource());
    }

}
