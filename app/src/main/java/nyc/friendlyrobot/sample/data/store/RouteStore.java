package nyc.friendlyrobot.sample.data.store;

import android.app.Application;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

import nyc.friendlyrobot.sample.data.model.Address;
import nyc.friendlyrobot.sample.data.model.Appointment;
import nyc.friendlyrobot.sample.data.model.ImmutableAppointment;
import nyc.friendlyrobot.sample.data.model.ImmutableRoute;
import nyc.friendlyrobot.sample.data.model.Route;
import nyc.friendlyrobot.sample.data.model.RouteModel;
import nyc.friendlyrobot.sample.data.model.Vehicle;
import nyc.friendlyrobot.sample.data.remote.Api;
import nyc.friendlyrobot.sample.interaction.RouteAction;
import rx.Observable;

import static nyc.friendlyrobot.sample.data.model.Route.MAPPER;


public class RouteStore extends BaseStore<Object, Object> {
    @Inject
    BriteDatabase db;
    @Inject
    Api api;

    @Inject
    Application application;
    @Inject
    RouteAction action;

    @Inject
    public RouteStore() {

    }

    @Override
    Observable<Object> fetch(Object o, String forceNetwork) {
        return null;
    }

//    Observable<RouteResponse> fetch(RouteRequest request, String forceNetwork) {
//        return api.routes(forceNetwork)
//                .doOnNext(action.seedDB());
//    }


    public Observable<Route> loadRouteFor(long routeId) {
        return db.createQuery(RouteModel.TABLE_NAME, RouteModel.SELECT_ROUTE, new String[]{String.valueOf(routeId)})
                .mapToOne(MAPPER::map)
                .flatMap(this::withEvents)
                .flatMap(this::withVehicle);

    }

    public Observable<List<Route>> loadRoutes() {
        return db.createQuery(RouteModel.TABLE_NAME, RouteModel.SELECT_ALL)
                .mapToList(MAPPER::map)
                .first()
                .flatMap(Observable::from)
                .flatMap(this::withEvents)
                .flatMap(this::withVehicle)
                .cast(Route.class)
                .toList();
    }

    private Observable<ImmutableRoute> withVehicle(Route route) {

        return db.createQuery(Vehicle.TABLE_NAME, Vehicle.SELECT_BY_ID, new String[]{String.valueOf(route.vehicle_id())})
                .mapToOne(Vehicle.MAPPER::map)
                .first()
                .map(((ImmutableRoute) route)::withVehicle);
    }

    public Observable<ImmutableRoute> withEvents(Route route) {
        return loadEvents(route.id())
                .map(((ImmutableRoute) route)::withAppointments);
    }

    @NonNull
    public Observable<List<Appointment>> loadEvents(Long routeId) {
        return db.createQuery(Appointment.TABLE_NAME, Appointment.SELECT_FOR_ROUTE, new String[]{String.valueOf(routeId)})
                .mapToList(Appointment.MAPPER::map)
                .first()
                .flatMap(Observable::from)
                .flatMap(this::apptWithAddress)
                .cast(Appointment.class)
                .toList();
    }

    private boolean noAppointments(Long routeId) {
        return !action.recordExists(Appointment.TABLE_NAME,
                Appointment.ROUTE_ID,
                String.valueOf(routeId));
    }

    @NonNull
    public Observable<Appointment> loadEventFor(long eventId) {
        return db.createQuery(Appointment.TABLE_NAME, Appointment.SELECT_FOR_ID, new String[]{String.valueOf(eventId)})
                .mapToOne(Appointment.MAPPER::map)
                .flatMap(this::apptWithAddress)
                .cast(Appointment.class);
    }


    private Observable<ImmutableAppointment> apptWithAddress(Appointment appointment) {
        return db.createQuery(Address.TABLE_NAME,
                Address.SELECT_BY_ID,
                new String[]{String.valueOf(appointment.address_id())})
                .mapToOne(Address.MAPPER::map)
                .first()
                .map(((ImmutableAppointment) appointment)::withAddress);
    }

}
