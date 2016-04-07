package nyc.friendlyrobot.anchor.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateDeserializer implements JsonDeserializer<Date> {
    public DateDeserializer() {
    }

    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return new Date(TimeUnit.MILLISECONDS.convert(json.getAsLong(),TimeUnit.SECONDS));
    }
}
