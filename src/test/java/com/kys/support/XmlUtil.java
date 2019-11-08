package com.kys.support;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * XML 처리 유틸
 * 
 * @author kys0213
 * @date   2019. 2. 14.
 */
public class XmlUtil {

	/**
	 * XML 파일을 Object 로 변환
	 * @param xmlFile
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T xmlToObject(String xmlFile, Class<T> clazz) throws JAXBException{
		InputStream stream = ResourceUtil.getStream(xmlFile);
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return clazz.cast(unmarshaller.unmarshal(stream));
		} finally{
			ResourceUtil.close(stream);
		}
	}

	/**
	 * XML Stream을 Object로 변환
	 * @param stream
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T xmlToObject(InputStream stream, Class<T> clazz) throws JAXBException{
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return clazz.cast(unmarshaller.unmarshal(stream));
		} finally{
			ResourceUtil.close(stream);
		}
	}

	/**
	 * Object를 
	 * @param obj
	 * @param os
	 * @throws JAXBException
	 */
	public static void objectToXml(Object obj, OutputStream os) throws JAXBException{

		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(obj, os);
		} finally{
			ResourceUtil.close(os);
		}
	}
}
