package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author Richard Haley III
 */
public class CustomSerializer implements JsonSerializer<Object>
{

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
