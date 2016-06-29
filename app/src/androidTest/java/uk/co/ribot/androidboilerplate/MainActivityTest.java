package uk.co.ribot.androidboilerplate;

import android.content.Intent;
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
import uk.co.ribot.androidboilerplate.test.common.TestComponentRule;
import uk.co.ribot.androidboilerplate.test.common.TestDataFactory;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<MainActivity>(MainActivity.class, false, false) {
                @Override
                protected Intent getActivityIntent() {
                    // Override the default intent so we pass a false flag for syncing so it doesn't
                    // start a sync service in the background that would affect  the behaviour of
                    // this test.
                    return MainActivity.getStartIntent(
                            InstrumentationRegistry.getTargetContext(), false);
                }
            };

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public final TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void listOfRibotsShows() {
        List<Ribot> testDataRibots = TestDataFactory.makeListRibots(20);
        when(component.getMockDataManager().getRibots())
                .thenReturn(Observable.just(testDataRibots));

        main.launchActivity(null);

        int position = 0;
        for (Ribot ribot : testDataRibots) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            String name = String.format("%s %s", ribot.profile().name().first(),
                    ribot.profile().name().last());
            onView(withText(name))
                    .check(matches(isDisplayed()));
            onView(withText(ribot.profile().email()))
                    .check(matches(isDisplayed()));
            position++;
        }
    }

}