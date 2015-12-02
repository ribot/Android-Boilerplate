package uk.co.ribot.androidboilerplate;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.test.common.TestDataFactory;
import uk.co.ribot.androidboilerplate.test.common.TestDataManager;
import uk.co.ribot.androidboilerplate.util.MockDependenciesHelper;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
public class DataManagerTest {

    private final MockDependenciesHelper mMockDependenciesHelper = new MockDependenciesHelper();
    private final TestDataManager mDataManager =
            new TestDataManager(mMockDependenciesHelper.getMockApplication());

    @Test
    public void syncRibotsEmitsValues() {
        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot(),
                TestDataFactory.makeRibot());
        stubSyncRibotsHelperCalls(ribots);

        TestSubscriber<Ribot> result = new TestSubscriber<>();
        mDataManager.syncRibots().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);
    }

    @Test
    public void syncRibotsCallsApiAndDatabase() {
        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot(),
                TestDataFactory.makeRibot());
        stubSyncRibotsHelperCalls(ribots);

        mDataManager.syncRibots().subscribe();
        // Verify right calls to helper methods
        verify(mDataManager.getRibotsService()).getRibots();
        verify(mDataManager.getDatabaseHelper()).setRibots(ribots);
    }

    @Test
    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
        when(mDataManager.getRibotsService().getRibots())
                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));

        mDataManager.syncRibots().subscribe(new TestSubscriber<Ribot>());
        // Verify right calls to helper methods
        verify(mDataManager.getRibotsService()).getRibots();
        verify(mDataManager.getDatabaseHelper(), never()).setRibots(anyListOf(Ribot.class));
    }

    private void stubSyncRibotsHelperCalls(List<Ribot> ribots) {
        // Stub calls to the ribot service and database helper.
        when(mDataManager.getRibotsService().getRibots())
                .thenReturn(Observable.just(ribots));
        when(mDataManager.getDatabaseHelper().setRibots(ribots))
                .thenReturn(Observable.from(ribots));
    }

}
