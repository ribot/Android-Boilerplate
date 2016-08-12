package com.incendiary.androidboilerplate.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import com.incendiary.androidboilerplate.data.local.DatabaseHelper;
import com.incendiary.androidboilerplate.data.model.Ribot;
import com.incendiary.androidboilerplate.data.remote.ApiService;

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