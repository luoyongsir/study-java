package com.test.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ProtoStuff序列化和反序列化工具类
 * 
 * @author Luo Yong
 * @dete 2017-03-12
 */
public final class ProtoStuffUtil {

    /**
     * 序列化对象
     * 
     * @param t
     * @return
     */
    public static <T> byte[] toByteArray(T t) {
        byte[] byteArray = null;
        if (t != null) {
            Schema<T> schema = getSchema((Class<T>) t.getClass());
            byteArray = ProtostuffIOUtil.toByteArray(t, schema, buf());
        }
        return byteArray;
    }

    /**
     * 序列化对象
     * 
     * @param t
     * @param buf
     * @return
     */
    public static <T> byte[] toByteArray(T t, LinkedBuffer buf) {
        byte[] byteArray = null;
        if (t != null) {
            Schema<T> schema = getSchema((Class<T>) t.getClass());
            byteArray = ProtostuffIOUtil.toByteArray(t, schema, buf);
        }
        return byteArray;
    }

    /**
     * 序列化列表
     * 
     * @param list
     * @return
     */
	public static <T> byte[] toByteArray(List<T> list) {
		byte[] byteArray = null;
		if (list != null && !list.isEmpty()) {
			Schema<T> schema = getSchema((Class<T>) list.get(0).getClass());
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				ProtostuffIOUtil.writeListTo(bos, list, schema, buf(BUF_SIZE * 8));
				byteArray = bos.toByteArray();
			} catch (Exception e) {
				throw new RuntimeException("序列化对象列表(" + list + ")发生异常!", e);
			}
		}
		return byteArray;
	}

    /**
     * 反序列化对象
     * 
     * @param byteArray
     * @param clazz
     * @return
     */
    public static <T> T toBean(byte[] byteArray, Class<T> clazz) {
        T instance = null;
        if (byteArray != null && byteArray.length > 0) {
            Schema<T> schema = getSchema(clazz);
            instance = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(byteArray, instance, schema);
        }
        return instance;
    }

    /**
     * 反序列化列表
     * 
     * @param byteArray
     * @param clazz
     * @return
     */
    public static <T> List<T> toList(byte[] byteArray, Class<T> clazz) {
        List<T> list = null;
		if (byteArray != null && byteArray.length > 0) {
			try (ByteArrayInputStream bais = new ByteArrayInputStream(byteArray)) {
				Schema<T> schema = getSchema(clazz);
				list = ProtostuffIOUtil.parseListFrom(bais, schema);
			} catch (IOException e) {
				throw new RuntimeException("反序列化对象列表发生异常!", e);
			}
		}
        return list;
    }

    private static final int BUF_SIZE = 1024;

    private static final ConcurrentMap<Class<?>, Schema<?>> SCHEMA = new ConcurrentHashMap<>();

    /**
     * 从缓存获取Schema / 创建Schema，并缓存起来
     * 
     * @param clazz
     * @return
     */
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) SCHEMA.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                SCHEMA.put(clazz, schema);
            }
        }
        return schema;
    }

    private static LinkedBuffer buf() {
        return LinkedBuffer.allocate(BUF_SIZE);
    }

    /**
     * 创建序列化所需的buffer
     * 
     * @param size
     *            buffer初始化大小
     * @return
     */
    private static LinkedBuffer buf(final int size) {
        if (size < BUF_SIZE) {
            // size不允许小于BUF_SIZE
            return buf();
        }
        return LinkedBuffer.allocate(size);
    }

    private ProtoStuffUtil() {
    }
}
