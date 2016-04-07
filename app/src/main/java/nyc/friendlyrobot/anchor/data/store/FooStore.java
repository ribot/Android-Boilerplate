package nyc.friendlyrobot.anchor.data.store;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.common.collect.ImmutableList;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

import nyc.friendlyrobot.anchor.data.model.Bar;
import nyc.friendlyrobot.anchor.data.model.Foo;
import nyc.friendlyrobot.anchor.data.model.FooModel;
import nyc.friendlyrobot.anchor.data.model.ImmutableFoo;
import nyc.friendlyrobot.anchor.data.model.ImmutableLoo;
import nyc.friendlyrobot.anchor.data.model.Loo;
import nyc.friendlyrobot.anchor.data.remote.Api;
import nyc.friendlyrobot.anchor.interaction.FooAction;
import rx.Observable;

import static nyc.friendlyrobot.anchor.data.model.Foo.MAPPER;


public class FooStore extends BaseStore<Foo, String> {
    @Inject
    BriteDatabase db;
    @Inject
    Api api;

    @Inject
    Application application;
    @Inject
    FooAction action;

    @Inject
    public FooStore() {

    }

    Observable<Foo> fetch(String forceNetwork, String request) {
//        return api.foo(forceNetwork)
//                .doOnNext(action::seedDb);
        ImmutableList.Builder<Loo> list = ImmutableList.builder();
        for (int i = 0; i < 15; i++) {
            list.add(ImmutableLoo.of(null,i,1,1));
        }
        return Observable.just(ImmutableFoo.builder()
                .updated_at(0)
                .id(1)
                .addAllLooList(list.build())
                .build());
    }


    public Observable<Foo> loadFooWithId(long fooId) {
        return db.createQuery(FooModel.TABLE_NAME, FooModel.SELECT_ROUTE, new String[]{String.valueOf(fooId)})
                .mapToOne(MAPPER::map)
                .flatMap(this::withLooList)
                .flatMap(this::withBar);

    }

    public Observable<List<Foo>> loadFoos() {
        return db.createQuery(FooModel.TABLE_NAME, FooModel.SELECT_ALL)
                .mapToList(MAPPER::map)
                .first()
                .flatMap(Observable::from)
                .flatMap(this::withLooList)
                .flatMap(this::withBar)
                .cast(Foo.class)
                .toList();
    }

    private Observable<Foo> withBar(Foo foo) {

        return db.createQuery(Bar.TABLE_NAME, Bar.SELECT_BY_ID,
                new String[]{String.valueOf(foo.bar_id())})
                .mapToOne(Bar.MAPPER::map)
                .first()
                .map(((ImmutableFoo) foo)::withBar);
    }

    public Observable<Foo> withLooList(Foo foo) {
        return loadEvents(foo.id())
                .map(((ImmutableFoo) foo)::withLooList);
    }

    @NonNull
    public Observable<List<Loo>> loadEvents(Long fooId) {
        return db.createQuery(Loo.TABLE_NAME, Loo.SELECT_FOR_ID, new String[]{String.valueOf(fooId)})
                .mapToList(Loo.MAPPER::map)
                .first()
                .flatMap(Observable::from)
                .cast(Loo.class)
                .toList();
    }

    private boolean noLoos(Long fooId) {
        return !action.recordExists(Loo.TABLE_NAME,
                Loo.FOO_ID,
                String.valueOf(fooId));
    }

    @NonNull
    public Observable<Loo> loadEventFor(long eventId) {
        return db.createQuery(Loo.TABLE_NAME, Loo.SELECT_FOR_ID, new String[]{String.valueOf(eventId)})
                .mapToOne(Loo.MAPPER::map)
                .cast(Loo.class);
    }


}
