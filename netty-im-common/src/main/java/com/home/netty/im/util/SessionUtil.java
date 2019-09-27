package com.home.netty.im.util;

import com.home.netty.im.constants.AttributeConstants;
import com.home.netty.im.domain.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:18 2019/9/23
 * @modified by:
 */
public class SessionUtil {
    /**
     * 存放userId，Channel对应
     */
    private static final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 存放groupId,ChannelGroup对应
     */
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();


    /**
     * 登录后关联用户ID和通道，同时在通道上添加用户会话信息
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel){
        channelMap.put(session.getUserId(), channel);
        channel.attr(AttributeConstants.SESSION).set(session);
    }


    /**
     * 用户退出后需要取消关联用户通道和移除会话信息
     * @param channel
     */
    public static void unBindSession(Channel channel){
        if (hasLogin(channel)){
            Session session = getSession(channel);
            channelMap.remove(session.getUserId());
            channel.attr(AttributeConstants.SESSION).set(null);
            System.out.println(session.getNikeName()+" : 退出登录！");
        }
    }


    /**
     * 是否登录成功过
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        return getSession(channel) != null;
    }


    /**
     * 获取会话信息
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel){
        return channel.attr(AttributeConstants.SESSION).get();
    }

    /**
     * 获取通道
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId){
        return channelMap.get(userId);
    }

    /**
     * 加入群组后，关联groupId和channelGroup
     */
    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup){
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    /**
     * 获取channelGroup
     * @param groupId
     * @return
     */
    public static ChannelGroup getChannelGroup(String groupId){
        return groupIdChannelGroupMap.get(groupId);
    }
}
