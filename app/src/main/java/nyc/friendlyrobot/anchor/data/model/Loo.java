package nyc.friendlyrobot.anchor.data.model;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Loo implements LooModel {
    public static final LooModel.Mapper<Loo> MAPPER =
            new LooModel.Mapper<>
                    ((LooModel.Mapper.Creator<Loo>) ImmutableLoo::of);

    public static final class Marshal extends LooModel.LooMarshal {}
}
