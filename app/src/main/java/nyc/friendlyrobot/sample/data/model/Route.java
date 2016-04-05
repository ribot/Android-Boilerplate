
package nyc.friendlyrobot.sample.data.model;

import android.support.annotation.Nullable;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Route implements RouteModel {
    public static final Mapper<Route> MAPPER = new Mapper<>(ImmutableRoute::of);

    @Value.Parameter(false)
    public abstract List<Appointment> appointments();

    @Nullable
    @Value.Parameter(false)
    public abstract Vehicle vehicle();


    public enum Status {
        created, confirmed, prepping, ready, outbound, inbound, completed
    }

    public static final class Marshal extends RouteMarshal {
    }
}
