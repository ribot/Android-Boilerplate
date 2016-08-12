package com.incendiary.androidboilerplate;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.observers.TestSubscriber;
import com.incendiary.androidboilerplate.data.DataManager;
import com.incendiary.androidboilerplate.data.local.DatabaseHelper;
import com.incendiary.androidboilerplate.data.model.Ribot;
import com.incendiary.androidboilerplate.data.remote.ApiService;
import com.incendiary.androidboilerplate.test.common.TestDataFactory;

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
@RunWith(MockitoJUnitRunner.class) public class DataManagerTest {

  @Mock DatabaseHelper mMockDatabaseHelper;
  @Mock ApiService mMockApiService;
  private DataManager mDataManager;

  @Before public void setUp() {
    mDataManager = new DataManager(mMockApiService, mMockDatabaseHelper);
  }

  @Test public void syncRibotsEmitsValues() {
    List<Ribot> ribots =
        Arrays.asList(TestDataFactory.makeRibot("r1"), TestDataFactory.makeRibot("r2"));
    stubSyncRibotsHelperCalls(ribots);

    TestSubscriber<Ribot> result = new TestSubscriber<>();
    mDataManager.syncRibots().subscribe(result);
    result.assertNoErrors();
    result.assertReceivedOnNext(ribots);
  }

  @Test public void syncRibotsCallsApiAndDatabase() {
    List<Ribot> ribots =
        Arrays.asList(TestDataFactory.makeRibot("r1"), TestDataFactory.makeRibot("r2"));
    stubSyncRibotsHelperCalls(ribots);

    mDataManager.syncRibots().subscribe();
    // Verify right calls to helper methods
    verify(mMockApiService).getRibots();
    verify(mMockDatabaseHelper).setRibots(ribots);
  }

  @Test public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
    when(mMockApiService.getRibots()).thenReturn(
        Observable.<List<Ribot>>error(new RuntimeException()));

    mDataManager.syncRibots().subscribe(new TestSubscriber<Ribot>());
    // Verify right calls to helper methods
    verify(mMockApiService).getRibots();
    verify(mMockDatabaseHelper, never()).setRibots(anyListOf(Ribot.class));
  }

  private void stubSyncRibotsHelperCalls(List<Ribot> ribots) {
    // Stub calls to the ribot service and database helper.
    when(mMockApiService.getRibots()).thenReturn(Observable.just(ribots));
    when(mMockDatabaseHelper.setRibots(ribots)).thenReturn(Observable.from(ribots));
  }
}
