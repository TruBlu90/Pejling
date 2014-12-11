package entities;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author Richard Haley III
 */
public class LayerSupertypeAdapter implements JsonSerializer<LayerSupertype>
{

//    private static final String CLASSNAME = "CLASSNAME";
//    private static final String INSTANCE = "INSTANCE";

    @Override
    public JsonElement serialize(LayerSupertype src, Type typeOfSrc, JsonSerializationContext context)
    {
//        JsonObject result = new JsonObject();
//        String className = src.getClass().getCanonicalName();
//        result.addProperty(CLASSNAME, className);
//        JsonElement element = context.serialize(src, src.getClass());
//        result.add(INSTANCE, element);
//        return result;
        Gson gson = new Gson();
        return gson.toJsonTree(src);
    }
}
