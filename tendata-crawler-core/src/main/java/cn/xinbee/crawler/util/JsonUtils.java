package cn.xinbee.crawler.util;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonUtils {
    
    private final static ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(Include.NON_NULL);
	
	public static String serialize(Object obj) {
		String val = null;
		try {
			val = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException ex) {
		    throw new RuntimeException(ex);
		} 
		return val;
	}

	public static <T> T deserialize(String val){
	    T obj = null;
        try {
            obj = objectMapper.readValue(val, new TypeReference<T>(){});
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return obj;
	}
	
	public static <T> T deserialize(String val, Class<T> clazz) {
		T obj = null;
		try {
			obj = objectMapper.readValue(val, clazz);
		}catch (IOException ex) {
		    throw new RuntimeException(ex);
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
    public static <T> T deserialize(String val, Class<? extends Collection> collectionClass, Class<?> elementClass) {
        T obj = null;
        try {
            obj = objectMapper.readValue(val, objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass));
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return obj;
    }
	
	private JsonUtils(){
	    
	}
}
