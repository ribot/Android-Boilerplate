package nyc.friendlyrobot.anchor.data.store;

import android.support.annotation.NonNull;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public final class RxCache<Request, Response> {//RxCache cache
    //in memory cache of data
    final Cache<Request, Observable<Response>> memory;

    private RxCache() {
        memory = CacheBuilder.newBuilder()
                .maximumSize(getCacheSize())
                .expireAfterAccess(getCacheTTL(), TimeUnit.MILLISECONDS)
                .build();
    }

    public static <Request, Response> RxCache<Request, Response> create() {
        return new RxCache<>();
    }

    /**
     * @return data from get
     */
    Observable<Response> get(@NonNull final Request request, Observable<Response> network) {
        try {
            return memory.get(request, network::cache);
        } catch (ExecutionException e) {
            //no-op
        }
        return Observable.empty();
    }

    void update(@NonNull final Request request, final Response data) {
        memory.put(request, Observable.just(data));
    }

    void clearMemory() {
        memory.invalidateAll();
    }

    void clearMemory(@NonNull Request request) {
        memory.invalidate(request);
    }

    /**
     * Default Cache TTL, can be overridden
     *
     * @return get cache ttl
     */
    long getCacheTTL() {
        return TimeUnit.HOURS.toMillis(24);
    }

    /**
     * Default mem cache is 1, can be overridden otherwise
     *
     * @return get cache size
     */
    int getCacheSize() {
        return 30;
    }
}
