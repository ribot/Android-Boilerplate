package nyc.friendlyrobot.anchor.util;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;
import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;

/**
 * object to track network status
 */
@Singleton
public class NetworkStatus extends BroadcastReceiver {

    private final ConnectivityManager connectivityManager;
    private final WifiManager wifiManager;
    private final BehaviorSubject<Boolean> onChangeSubject = BehaviorSubject.create();

    @Inject
    public NetworkStatus(Application context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
    }

    public Observable<Void> onConnect() {
        return onChange().filter(connected -> connected).map((Func1<Boolean, Void>) connected -> null);
    }

    public Observable<Void> onDisconnect() {
        return onChange().filter(connected -> !connected).map((Func1<Boolean, Void>) connected -> null);
    }

    public Observable<Boolean> onChange() {
        return onChangeSubject.distinctUntilChanged();
    }

    public String getNetworkStatus() {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo == null) ? "None" : getNetworkStatus(netInfo);
    }

    public boolean isInternetConnected() {
        return isOnGoodConnection(); // seems weird that these are the same
    }

    public boolean isOnGoodConnection() {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && isOnGoodConnection(netInfo);
    }

    public boolean isOnGoodWIFIConnection() {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && isOnGoodWIFIConnection(netInfo);
    }

    private boolean isOnGoodConnection(@NonNull NetworkInfo netInfo) {
        return netInfo.isConnected() && (isOnGoodWIFIConnection(netInfo) || isOnGoodCellularConnection(netInfo));
    }

    private boolean isOnGoodCellularConnection(@NonNull NetworkInfo netInfo) {
        return netInfo.getSubtype() != NETWORK_TYPE_1xRTT && netInfo.getSubtype() != NETWORK_TYPE_EDGE;
    }

    private boolean isOnGoodWIFIConnection(@NonNull NetworkInfo netInfo) {
        return (netInfo.getType() == TYPE_WIFI)
                && (WifiManager.calculateSignalLevel(wifiManager.getConnectionInfo().getRssi(), 4) > 0);
    }

    private String getNetworkStatus(@NonNull NetworkInfo netInfo) {
        return (netInfo.getType() == TYPE_MOBILE) ? netInfo.getSubtypeName() : netInfo.getTypeName();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isInternetConnected = isInternetConnected();
        onChangeSubject.onNext(isInternetConnected);
    }

}
