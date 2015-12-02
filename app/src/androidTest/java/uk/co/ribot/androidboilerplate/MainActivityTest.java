package uk.co.ribot.androidboilerplate;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.test.common.TestDataFactory;
import uk.co.ribot.androidboilerplate.test.common.injection.module.ApplicationTestModule;
import uk.co.ribot.androidboilerplate.test.common.rules.ClearDataRule;
import uk.co.ribot.androidboilerplate.test.common.rules.TestComponentRule;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final TestComponentRule component = new TestComponentRule(
            InstrumentationRegistry.getTargetContext(),
            ApplicationTestModule.DataManagerTestStrategy.SPY);
    public final ClearDataRule clearDataRule = new ClearDataRule(component);
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public final TestRule chain = RuleChain.outerRule(component).around(clearDataRule).around(main);

    @Test
    public void listOfRibotsShows() {
        List<Ribot> mockRibots = TestDataFactory.makeListRibots(20);
        when(component.getMockRibotsService().getRibots())
                .thenReturn(Observable.just(mockRibots));

        main.launchActivity(null);

        int position = 0;
        for (Ribot mockRibot : mockRibots) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            String name = String.format("%s %s", mockRibot.profile.name.first,
                    mockRibot.profile.name.last);
            onView(withText(name))
                    .check(matches(isDisplayed()));
            onView(withText(mockRibot.profile.email))
                    .check(matches(isDisplayed()));
            position++;
        }
    }

}