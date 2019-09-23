package com.home.netty.im.util;

import com.home.netty.im.constants.AttributeConstants;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:18 2019/9/23
 * @modified by:
 */
public class LoginUtil {

    /**
     * 用户登录成功后标记
     * @param channel
     */
    public static void markAsLogin(Channel channel){
        channel.attr(AttributeConstants.LOGIN).set(Boolean.TRUE);
    }

    /**
     * 是否登录成功过
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> attribute = channel.attr(AttributeConstants.LOGIN);
        return attribute.get() != null;
    }

}
