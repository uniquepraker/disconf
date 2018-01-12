package com.baidu.disconf.web.service.user.dao;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;

/**
 * Created by praker on 05/01/2018.
 */
public interface UserDao extends BaseDao<Long, User> {

    void executeSql(String sql);

    User getUserByName(String name);

}
