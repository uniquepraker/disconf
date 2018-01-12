package com.baidu.disconf.web.service.sign.service;

import com.baidu.disconf.web.service.user.bo.User;

/**
 * Created by praker on 04/01/2018.
 */
public interface SignMgr {

    User getUserByName(String name);

    boolean validate(String userPassword, String passwordToBeValidate);

    User signin(String phone);

}
