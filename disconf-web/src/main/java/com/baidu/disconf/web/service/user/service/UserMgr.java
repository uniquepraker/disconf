package com.baidu.disconf.web.service.user.service;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.disconf.web.service.user.vo.VisitorVo;

import java.util.List;

/**
 * Created by praker on 04/01/2018.
 */
public interface UserMgr {

    /**
     * 获取用户的基本信息（登录用户）
     *
     * @return
     */
    Visitor getVisitor(Long userId);

    VisitorVo getCurVisitor();

    User getUser(Long userId);

    /**
     * @return
     */
    Long create(User user);

    /**
     * @param user
     */
    void create(List<User> user);

    /**
     * @return
     */
    List<User> getAll();

    /**
     * 为某个user添加一个app
     *
     * @param userId
     */
    String addOneAppForUser(Long userId, int appId);

    /**
     * 修改密码
     *
     * @param newPassword
     */
    void modifyPassword(Long userId, String newPassword);




}
