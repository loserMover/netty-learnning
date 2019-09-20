package com.home.netty.im.serialize;

import com.home.netty.im.serialize.support.JSONSerializer;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:20 2019/9/20
 * @modified by:
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();


    /**
     * java 对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成java 对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
