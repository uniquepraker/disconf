package com.baidu.disconf.web.service.sign.service.impl;

import com.baidu.disconf.web.service.sign.dao.SignDao;
import com.baidu.disconf.web.service.sign.service.SignMgr;
import com.baidu.disconf.web.service.sign.utils.SignUtils;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by praker on 05/01/2018.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SignMgrImpl implements SignMgr {


    protected static final Logger LOG = LoggerFactory.getLogger(SignMgrImpl.class);


    @Autowired
    private SignDao signDao;

    @Autowired
    private UserDao userDao;


    /**
     * 根据 用户名获取用户
     */
    public User getUserByName(String name) {

        return userDao.getUserByName(name);
    }

    /**
     * 验证密码是否正确
     *
     * @param userPassword
     * @param passwordToBeValidate
     *
     * @return
     */
    public boolean validate(String userPassword, String passwordToBeValidate) {

        String data = SignUtils.createPassword(passwordToBeValidate);

        if (data.equals(userPassword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 登录
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User signin(String phone) {

        //
        // 获取用户
        //
        User user = userDao.getUserByName(phone);

        userDao.update(user);

        return user;
    }
}
