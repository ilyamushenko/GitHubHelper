package vsu.tp.tgbot.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

class MyNullKeySerializer extends JsonSerializer<Object>
{
    @Override
    public void serialize(Object nullKey, JsonGenerator jsonGenerator, SerializerProvider unused)
            throws IOException
    {
        jsonGenerator.writeFieldName("");
    }
}

//public class Utils {
//    public String getJsonFromObject(Object obj) {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        mapper.getSerializerProvider().setNullKeySerializer(new MyNullKeySerializer());
//        return mapper.writeValueAsString(obj);
//    }
//}
