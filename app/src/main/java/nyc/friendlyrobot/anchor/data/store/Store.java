package nyc.friendlyrobot.anchor.data.store;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Created by 206847 on 12/19/15.
 */
public interface Store<T, V> {
    Observable<T> get(@NonNull V requestObject);

    Observable<T> fresh(@NonNull V requestObject);
}
