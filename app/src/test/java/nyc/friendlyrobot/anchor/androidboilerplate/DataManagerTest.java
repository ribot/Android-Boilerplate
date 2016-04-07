package nyc.friendlyrobot.anchor.androidboilerplate;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {
//
//    @Mock DatabaseHelper mMockDatabaseHelper;
//    @Mock PreferencesHelper mMockPreferencesHelper;
//    @Mock
//    API mMockAPI;
//    @Mock EventPosterHelper mEventPosterHelper;
//    private DataManager mDataManager;
//
//    @Before
//    public void setUp() {
//        mDataManager = new DataManager(mMockAPI, mMockPreferencesHelper,
//                mMockDatabaseHelper, mEventPosterHelper);
//    }
//
//    @Test
//    public void syncRibotsEmitsValues() {
//        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot("r1"),
//                TestDataFactory.makeRibot("r2"));
//        stubSyncRibotsHelperCalls(ribots);
//
//        TestSubscriber<Ribot> result = new TestSubscriber<>();
//        mDataManager.syncRibots().subscribe(result);
//        result.assertNoErrors();
//        result.assertReceivedOnNext(ribots);
//    }
//
//    @Test
//    public void syncRibotsCallsApiAndDatabase() {
//        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot("r1"),
//                TestDataFactory.makeRibot("r2"));
//        stubSyncRibotsHelperCalls(ribots);
//
//        mDataManager.syncRibots().subscribe();
//        // Verify right calls to helper methods
//        verify(mMockAPI).getRibots();
//        verify(mMockDatabaseHelper).setRibots(ribots);
//    }
//
//    @Test
//    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
//        when(mMockAPI.getRibots())
//                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));
//
//        mDataManager.syncRibots().subscribe(new TestSubscriber<Ribot>());
//        // Verify right calls to helper methods
//        verify(mMockAPI).getRibots();
//        verify(mMockDatabaseHelper, never()).setRibots(anyListOf(Ribot.class));
//    }
//
//    private void stubSyncRibotsHelperCalls(List<Ribot> ribots) {
//        // Stub calls to the ribot service and database helper.
//        when(mMockAPI.getRibots())
//                .thenReturn(Observable.just(ribots));
//        when(mMockDatabaseHelper.setRibots(ribots))
//                .thenReturn(Observable.from(ribots));
//    }

}
