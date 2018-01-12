package com.baidu.disconf.web.service.user.dao.impl;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dao.UserDao;
import com.baidu.dsp.common.dao.AbstractDao;
import com.baidu.dsp.common.dao.Columns;
import org.springframework.stereotype.Repository;

/**
 * Created by praker on 05/01/2018.
 */
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    public void executeSql(String sql) {
        executeSQL(sql, null);
    }


    public User getUserByName(String name) {
        return findOne(match(Columns.NAME, name));
    }

}
