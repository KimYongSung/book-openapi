package com.kys.support;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * ObjectMapper Config
 *
 * @author 	kys0213
 * @since	2016. 9. 26.
 */
@SuppressWarnings("serial")
public class CustomObjectMapper extends ObjectMapper {

	public CustomObjectMapper(){
		super.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		super.configure(SerializationFeature.INDENT_OUTPUT, false);
		super.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		super.setSerializationInclusion(Include.NON_NULL);
		super.setVisibility(super.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
				);
	}
}
