package com.kys.support;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;

public class JsonUtil {
	
	private static final ObjectMapper objectMapper = new CustomObjectMapper();
	
	/**
	 * Object를 json 형식 문자열로 변환
	 * @param obj
	 * @return 
	 * @throws JsonProcessingException
	 */
	public static String objectToJson(Object obj) throws JsonProcessingException{
		return objectMapper.writeValueAsString(obj);
	}
	
	/**
	 * Object를 json 형식으로 OutputStream 에 write
	 * @param os
	 * @param obj
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static void objectToJson(OutputStream os, Object obj) throws JsonGenerationException, JsonMappingException, IOException{
		objectMapper.writeValue(os, obj);
	}
	
	/**
	 * json 문자열을 읽어서 Object로 변환
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <R> R jsonToObject(String json, Class<R> clazz) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, clazz);
	}
	
	/**
	 * json 문자열을 읽어서 Object로 변환
	 * @param json
	 * @param javaType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <R> R jsonToObject(String json, JavaType javaType) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, javaType);
	}
	
	
	/**
	 * json stream을 읽어서 Object로 변환
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <R> R jsonToObject(InputStream json, Class<R> clazz) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, clazz);
	}
	
	/**
	 * json stream을 읽어서 Object로 변환
	 * @param json
	 * @param javaType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <R> R jsonToObject(InputStream json, JavaType javaType) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, javaType);
	}
	
	/**
	 * json reader를 읽어서 Object로 변환
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <R> R jsonToObject(Reader json, Class<R> clazz) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, clazz);
	}
	
	/**
	 * json reader를 읽어서 Object로 변환
	 * @param json
	 * @param javaType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <R> R jsonToObject(Reader json, JavaType javaType) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(json, javaType);
	}
	
	@SuppressWarnings("rawtypes")
	public static JavaType getCollectionType(Class<? extends Collection> collectionClass, Class<?> element){
		return objectMapper.getTypeFactory().constructCollectionType(collectionClass, element);
	}
	
	@SuppressWarnings("rawtypes")
	public static JavaType getMapType(Class<? extends Map> mapClass, Class<?> key, Class<?> value){
		return objectMapper.getTypeFactory().constructMapType(mapClass, key, value);
	}
}
