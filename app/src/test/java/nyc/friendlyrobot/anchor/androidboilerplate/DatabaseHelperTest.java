package nyc.friendlyrobot.anchor.androidboilerplate;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import nyc.friendlyrobot.anchor.util.DefaultConfig;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

//    private final DatabaseHelper mDatabaseHelper =
//            new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application));
//
//    @Before
//    public void setUp() {
//        mDatabaseHelper.clearTables().subscribe();
//    }
//
//    @Test
//    public void setRibots() {
//        Ribot ribot1 = TestDataFactory.makeRibot("r1");
//        Ribot ribot2 = TestDataFactory.makeRibot("r2");
//        List<Ribot> ribots = Arrays.asList(ribot1, ribot2);
//
//        TestSubscriber<Ribot> result = new TestSubscriber<>();
//        mDatabaseHelper.setRibots(ribots).subscribe(result);
//        result.assertNoErrors();
//        result.assertReceivedOnNext(ribots);
//
//        Cursor cursor = mDatabaseHelper.getBriteDb()
//                .query("SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME);
//        assertEquals(2, cursor.getCount());
//        for (Ribot ribot : ribots) {
//            cursor.moveToNext();
//            assertEquals(ribot.profile, Db.RibotProfileTable.parseCursor(cursor));
//        }
//    }
//
//    @Test
//    public void getRibots() {
//        Ribot ribot1 = TestDataFactory.makeRibot("r1");
//        Ribot ribot2 = TestDataFactory.makeRibot("r2");
//        List<Ribot> ribots = Arrays.asList(ribot1, ribot2);
//
//        mDatabaseHelper.setRibots(ribots).subscribe();
//
//        TestSubscriber<List<Ribot>> result = new TestSubscriber<>();
//        mDatabaseHelper.getRibots().subscribe(result);
//        result.assertNoErrors();
//        result.assertValue(ribots);
//    }

}
