
package nyc.friendlyrobot.anchor.data.model;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Bar implements BarModel{
    public static final BarModel.Mapper<Bar> MAPPER =
            new BarModel.Mapper<>((Mapper.Creator<Bar>) ImmutableBar::of);

    public static final class Marshal extends BarModel.BarMarshal {
    }
}
