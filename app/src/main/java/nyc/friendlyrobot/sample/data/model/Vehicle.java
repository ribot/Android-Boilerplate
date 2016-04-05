
package nyc.friendlyrobot.sample.data.model;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Vehicle implements VehicleModel{
    public static final VehicleModel.Mapper<Vehicle> MAPPER =
            new VehicleModel.Mapper<>((Mapper.Creator<Vehicle>) ImmutableVehicle::of);

    public static final class Marshal extends VehicleModel.VehicleMarshal {
    }
}
