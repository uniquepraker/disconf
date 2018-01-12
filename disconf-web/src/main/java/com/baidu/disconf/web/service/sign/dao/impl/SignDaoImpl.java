package com.baidu.disconf.web.service.sign.dao.impl;

import com.baidu.disconf.web.service.sign.dao.SignDao;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.dsp.common.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
@Repository
public class SignDaoImpl extends AbstractDao<Long, User> implements SignDao {

}