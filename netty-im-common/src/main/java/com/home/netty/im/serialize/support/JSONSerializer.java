package com.home.netty.im.serialize.support;

import com.alibaba.fastjson.JSON;
import com.home.netty.im.serialize.Serializer;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:31 2019/9/20
 * @modified by:
 */
public class JSONSerializer implements Serializer{

    @Override
    public byte getSerializerAlgorithm() {
        return 0;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
