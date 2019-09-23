package com.home.netty.im.constants;

import io.netty.util.AttributeKey;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:21 2019/9/23
 * @modified by:
 */
public interface AttributeConstants {

    /**
     * 用户登录标识
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");


}
