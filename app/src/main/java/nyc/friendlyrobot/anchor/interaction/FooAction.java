package nyc.friendlyrobot.anchor.interaction;

import javax.inject.Inject;
import javax.inject.Singleton;

import nyc.friendlyrobot.anchor.data.model.Foo;
import rx.Observable;

@Singleton
public class FooAction extends Interaction {

    @Inject
    public FooAction() {
    }

    public Observable<Boolean> started(long eventId) {
        int id = db.update(Foo.TABLE_NAME, new Foo.Marshal()
                     .updated_at(0)
                        .asContentValues(),
                Foo.ID + "=" + eventId);
//        api.fakeUpdate(eventId);
        return Observable.just(id > 0);
    }




    public long seedDb(Foo foo) {
        if (recordExists(Foo.TABLE_NAME, Foo.ID, String.valueOf(foo.id()))) {
            return 0;
        }
        return db.insert(Foo.TABLE_NAME, new Foo.Marshal()
                .id(foo.id())
                .name(foo.name())
                .updated_at(foo.updated_at())
                .bar_id(foo.bar_id()).asContentValues());
    }
}
