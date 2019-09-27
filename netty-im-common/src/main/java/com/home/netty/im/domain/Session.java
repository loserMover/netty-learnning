package com.home.netty.im.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 10:09 2019/9/27
 * @modified by:
 */
@Data
@NoArgsConstructor
public class Session implements Serializable {

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 用户登录账号
     */
    private String userName;

    /**
     * 用户密码
     */
    @JSONField(serialize = false, deserialize = false)
    private String password;

    /**
     * 用户昵称
     */
    private String nikeName;


    public Session(String userId, String userName, String password, String nikeName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nikeName = nikeName;
    }
}
