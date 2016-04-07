package nyc.friendlyrobot.anchor.data.store;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * RxStore to be used for loading an object different data sources
 *
 * @param <Response> data type after parsing
 *            <p>
 *            get = cached data if not stale otherwise network, updates caches
 *            network=skip memory and disk cache, still updates caches
 */
public abstract class BaseStore<Response, Request> implements Store<Response, Request> {
    public static final String SUCCESS_RESPONSE = "SUCCESS";
    private final RxCache<Request, Response> cache;


    public BaseStore() {
        cache = RxCache.create();
    }

    /**
     * @return an observable from the first data source
     * memory/disk/network that is available and not stale
     */
    @Override
    public Observable<Response> get(Request request) {
        return cache.get(request, getNetworkResponse(request));
    }

    /**
     * @return force network and update disk/memory
     */
    @Override
    public Observable<Response> fresh(@NonNull Request request) {
        return fetch(request, "fresh and clean")
                .doOnNext(data -> cache.update(request, data));
    }

    protected Observable<Response> getNetworkResponse(@NonNull final Request request) {
        return fetch(request, null).doOnNext(response -> {
          //nothing
        });
    }

     abstract Observable<Response> fetch(Request request, String forceNetwork);

    public void clearMemory() {
        cache.clearMemory();
    }

    public void clearMemory(@NonNull final Request request) {
        cache.clearMemory(request);
    }

}
