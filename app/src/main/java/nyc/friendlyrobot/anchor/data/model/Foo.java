
package nyc.friendlyrobot.anchor.data.model;

import android.support.annotation.Nullable;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(allParameters = true)
public abstract class Foo implements FooModel {
    public static final Mapper<Foo> MAPPER = new Mapper<>(ImmutableFoo::of);

    @Value.Parameter(false)
    public abstract List<Loo> looList();

    @Nullable
    @Value.Parameter(false)
    public abstract Bar bar();


    public static final class Marshal extends FooMarshal {
    }
}
