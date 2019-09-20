package com.home.netty.im.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:18 2019/9/20
 * @modified by:
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 获取指令
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();

}
