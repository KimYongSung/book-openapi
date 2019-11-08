package com.kys.support;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Map Util
 * @author kys0213
 * @since  2017. 11. 30.
 */
public class MapUtil {
	
	private final static int DEFAULT_INITIAL_CAPACITY = 16;

	public static <K,V> Map<K , V> emptyHashMap(){
		return emptyHashMap(DEFAULT_INITIAL_CAPACITY);
	}
	
	public static <K,V> Map<K , V> emptyHashMap(int capacity){
		return new HashMap<K, V>(capacity);
	}
	
	public static <K,V> Map<K , V> emptyLinkedHashMap(){
		return emptyLinkedHashMap(DEFAULT_INITIAL_CAPACITY);
	}
	
	public static <K,V> Map<K , V> emptyLinkedHashMap(int capacity){
		return new LinkedHashMap<K, V>(capacity);
	}
	
	/**
	 * Map 형태의 데이터를 특정 VO 로 변환.
	 * @param param
	 * @param t
	 */
	public static <T, V> void mapToObject(Map<String, V> param, T t){
		Iterator<String> keySet = param.keySet().iterator();
		while(keySet.hasNext()){
			String key = keySet.next();
			Field field = ReflectionUtil.findField(t.getClass(), key);
			ReflectionUtil.setField(field, t, param.get(key));
		}
	}
	
	/**
	 * Map 형태의 데이터를 특정 VO 로 변환.
	 * @param param
	 * @param clazz
	 */
	public static <T, V> T mapToObject(Map<String, V> param, Class<T> clazz){
		T newInstance = ReflectionUtil.newInstance(clazz);
		
		mapToObject(param, newInstance);
		
		return newInstance;
	}
	
	/**
	 * VO를 Map<String,Object>로 변환
	 * @param t
	 * @return
	 */
	public static <T> Map<String, ?> objectToMap(T t){
		Field[] fields = ReflectionUtil.findFields(t.getClass());
		Map<String, Object> map = emptyHashMap(fields.length);
		for (Field field : fields) {
			map.put(field.getName(), ReflectionUtil.getField(field, t));
		}
		return map;
	}

}
