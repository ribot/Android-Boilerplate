
package nyc.friendlyrobot.sample.data.model;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Address implements AddressModel{
    public static final Mapper<Address> MAPPER =
            new Mapper<>((Mapper.Creator<Address>) ImmutableAddress::of);

    public static final class Marshal extends AddressMarshal {
    }
}
