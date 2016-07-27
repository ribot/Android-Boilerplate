package uk.co.ribot.androidboilerplate.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.ApiService;

@Singleton public class DataManager {

  private final ApiService mApiService;
  private final DatabaseHelper mDatabaseHelper;
  private final PreferencesHelper mPreferencesHelper;

  @Inject public DataManager(ApiService apiService, PreferencesHelper preferencesHelper,
      DatabaseHelper databaseHelper) {
    mApiService = apiService;
    mPreferencesHelper = preferencesHelper;
    mDatabaseHelper = databaseHelper;
  }

  public PreferencesHelper getPreferencesHelper() {
    return mPreferencesHelper;
  }

  public Observable<Ribot> syncRibots() {
    return mApiService.getRibots().concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
      @Override public Observable<Ribot> call(List<Ribot> ribots) {
        return mDatabaseHelper.setRibots(ribots);
      }
    });
  }

  public Observable<List<Ribot>> getRibots() {
    return mDatabaseHelper.getRibots().distinct();
  }
}