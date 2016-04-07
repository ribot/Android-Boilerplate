package nyc.friendlyrobot.anchor.data.store;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Base class for handling Raw and Parsed data subscription logic
 *
 * @param <Response> data type after parsing
 */
public abstract class StreamedStore<Response, Request> extends BaseStore<Response, Request> {

    private final BehaviorSubject<Response> subject;

    public StreamedStore() {
        super();
        subject = BehaviorSubject.create();
    }

    /**
     * To be exposed to clients for subscribing to endless data streams.
     * Any client that subscribers to the steam will receive updates for all data of
     * type {@link Response}
     */
    public Observable<Response> stream() {
        return subject.asObservable();
    }

    @Override
    protected Observable<Response> getNetworkResponse(@NonNull Request request) {
        return super.getNetworkResponse(request)
                .doOnNext(this::notifySubscribers);
    }

    /**
     * Notify the subscribers of the subject of data change
     *
     * @param data to be emitted to subscribers of Subject
     */
    protected void notifySubscribers(Response data) {
        subject.onNext(data);
    }
}
