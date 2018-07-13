package com.test.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Jackson 处理json
 *
 * @author Luo Yong
 * @date 2017-03-12
 */
public final class JacksonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class.getName());

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 忽略空字段
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 将对象转换成json
     *
     * @param object
     * @return
     */
    public static String toJson(final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("对象转json出错：", e);
        }
        return null;
    }

    /**
     * 将json转换成bean对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(final String json, Class<T> clazz) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return mapper.readValue(json, clazz);
            }
        } catch (IOException e) {
            LOG.error("json转对象出错：", e);
        }
        return null;
    }

    /**
     * 将json转换成Map<Bean, Bean>对象 <br />
     * 或 将json转换成Collection<Bean>对象
     *
     * @param json
     * @param javaType
     * @return
     */
    public static <T> T jsonToBean(final String json, JavaType javaType) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return mapper.readValue(json, javaType);
            }
        } catch (IOException e) {
            LOG.error("json转对象{}出错：", javaType.toString(), e);
        }
        return null;
    }

    /**
     * 构造Collection类型.
     */
    public static JavaType buildCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * 将json转换成Collection<Bean>对象
     *
     * @param json
     * @param collectionClass
     * @param elementClass
     * @return
     */
    public static <T> T jsonToList(final String json, Class<? extends Collection> collectionClass, Class<?> elementClass) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return mapper.readValue(json, buildCollectionType(collectionClass, elementClass));
            }
        } catch (IOException e) {
            LOG.error("json转List对象出错：", e);
        }
        return null;
    }

    /**
     * 构造Map类型.
     */
    public static JavaType buildMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 将json转换成Map<Bean, Bean>对象
     *
     * @param json
     * @param mapClass
     * @param keyClass
     * @param valueClass
     * @return
     */
    public static <T> T jsonToMap(final String json, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return mapper.readValue(json, buildMapType(mapClass, keyClass, valueClass));
            }
        } catch (IOException e) {
            LOG.error("json转Map对象出错：", e);
        }
        return null;
    }

    private JacksonUtil() {
    }
}
