package nyc.friendlyrobot.sample.interaction;

import javax.inject.Inject;
import javax.inject.Singleton;

import nyc.friendlyrobot.sample.data.model.Route;
import rx.Observable;

@Singleton
public class RouteAction extends Interaction {

    @Inject
    public RouteAction() {
    }

    public Observable<Boolean> started(long eventId) {
        int id = db.update(Route.TABLE_NAME, new Route.Marshal()
                        .status("fake")
                        .asContentValues(),
                Route.ID + "=" + eventId);
//        api.fakeUpdate(eventId);
        return Observable.just(id > 0);
    }




    private long insertRoute(Route route) {
        if (recordExists(Route.TABLE_NAME, Route.ID, String.valueOf(route.id()))) {
            return 0;
        }
        return db.insert(Route.TABLE_NAME, new Route.Marshal()
                .company_id(route.company_id())
                .created_at(route.created_at())
                .created_by(route.created_by())
                .driver_id(route.driver_id())
                .id(route.id())
                .modified_by(route.modified_by())
                .name(route.name())
                .status(route.status())
                .updated_at(route.updated_at())
                .vehicle_id(route.vehicle_id()).asContentValues());
    }



}
