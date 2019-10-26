package vsu.tp.tgbot.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class Utils {
    public static boolean stringBeginWith(String mainString, String partString) {
        String mString = mainString.toLowerCase();
        String pString = partString.toLowerCase();
        int i = 0;
        for (i = 0; i < partString.length(); i++) {
            if (mString.charAt(0) != pString.charAt(0)) {
                break;
            }
        }
        return i==partString.length();
    }
}
