package nyc.friendlyrobot.anchor.androidboilerplate;

import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

//    public final TestComponentRule component =
//            new TestComponentRule(InstrumentationRegistry.getTargetContext());
//    public final ActivityTestRule<MainActivity> main =
//            new ActivityTestRule<MainActivity>(MainActivity.class, false, false) {
//                @Override
//                protected Intent getActivityIntent() {
//                    // Override the default intent so we pass a false flag for syncing so it doesn't
//                    // start a sync service in the background that would affect  the behaviour of
//                    // this test.
//                    return MainActivity.getStartIntent(
//                            InstrumentationRegistry.getTargetContext(), false);
//                }
//            };
//
//    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
//    // in the Application before any Activity is launched.
//    @Rule
//    public final TestRule chain = RuleChain.outerRule(component).around(main);
//
//    @Test
//    public void listOfRibotsShows() {
//        List<Ribot> testDataRibots = TestDataFactory.makeListRibots(20);
//        when(component.getMockDataManager().getRibots())
//                .thenReturn(Observable.just(testDataRibots));
//
//        main.launchActivity(null);
//
//        int position = 0;
//        for (Ribot ribot : testDataRibots) {
//            onView(withId(R.id.recycler_view))
//                    .perform(RecyclerViewActions.scrollToPosition(position));
//            String name = String.format("%s %s", ribot.profile.name.first,
//                    ribot.profile.name.last);
//            onView(withText(name))
//                    .check(matches(isDisplayed()));
//            onView(withText(ribot.profile.email))
//                    .check(matches(isDisplayed()));
//            position++;
//        }
//    }

}
