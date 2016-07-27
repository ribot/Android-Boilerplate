package uk.co.ribot.androidboilerplate.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.ApiService;

@Singleton public class DataManager {

  private final ApiService mApiService;
  private final DatabaseHelper mDatabaseHelper;

  @Inject public DataManager(ApiService apiService, DatabaseHelper databaseHelper) {
    mApiService = apiService;
    mDatabaseHelper = databaseHelper;
  }

  public Observable<Ribot> syncRibots() {
    return mApiService.getRibots().concatMap(mDatabaseHelper::setRibots);
  }

  public Observable<List<Ribot>> getRibots() {
    return mDatabaseHelper.getRibots().distinct();
  }
}