package nyc.friendlyrobot.sample.data.model;

import android.support.annotation.Nullable;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Appointment implements AppointmentModel {
    public static final AppointmentModel.Mapper<Appointment> MAPPER =
            new AppointmentModel.Mapper<>
                    ((AppointmentModel.Mapper.Creator<Appointment>) ImmutableAppointment::of);


    @Nullable
    @Value.Parameter(false)
    public abstract Address address();

    public enum Status {
        confirmed, multiwh, routed, error,
        started, arrived, completed, failed, canceled, voided
    }

    public Status getStatus() {
        return Status.valueOf(status());
    }

    public static final class Marshal extends AppointmentModel.AppointmentMarshal {}
}
