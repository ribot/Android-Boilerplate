package com.incendiary.androidboilerplate.data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import javax.inject.Inject;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import com.incendiary.androidboilerplate.BoilerplateApplication;
import com.incendiary.androidboilerplate.data.model.Ribot;
import com.incendiary.androidboilerplate.util.AndroidComponentUtil;
import com.incendiary.androidboilerplate.util.NetworkUtil;

public class SyncService extends Service {

  @Inject DataManager mDataManager;
  private Subscription mSubscription;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, SyncService.class);
  }

  public static boolean isRunning(Context context) {
    return AndroidComponentUtil.isServiceRunning(context, SyncService.class);
  }

  @Override public void onCreate() {
    super.onCreate();
    BoilerplateApplication.component().inject(this);
  }

  @Override public int onStartCommand(Intent intent, int flags, final int startId) {
    Timber.i("Starting sync...");

    if (!NetworkUtil.isNetworkConnected(this)) {
      Timber.i("Sync canceled, connection not available");
      AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
      stopSelf(startId);
      return START_NOT_STICKY;
    }

    if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    mSubscription =
        mDataManager.syncRibots().subscribeOn(Schedulers.io()).subscribe(new Observer<Ribot>() {
          @Override public void onCompleted() {
            Timber.i("Synced successfully!");
            stopSelf(startId);
          }

          @Override public void onError(Throwable e) {
            Timber.w(e, "Error syncing.");
            stopSelf(startId);
          }

          @Override public void onNext(Ribot ribot) {
          }
        });

    return START_STICKY;
  }

  @Override public void onDestroy() {
    if (mSubscription != null) mSubscription.unsubscribe();
    super.onDestroy();
  }

  @Override public IBinder onBind(Intent intent) {
    return null;
  }

  public static class SyncOnConnectionAvailable extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)
          && NetworkUtil.isNetworkConnected(context)) {
        Timber.i("Connection is now available, triggering sync...");
        AndroidComponentUtil.toggleComponent(context, this.getClass(), false);
        context.startService(getStartIntent(context));
      }
    }
  }
}