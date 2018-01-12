package com.baidu.unbiz.common.genericdao.dao;

import com.baidu.unbiz.common.genericdao.bo.InsertOption;
import com.github.knightliao.apollo.db.bo.BaseObject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by praker on 05/01/2018.
 */
public interface BaseDao<KEY extends Serializable, ENTITY extends BaseObject<KEY>> {

    ENTITY get(KEY id);

    List<ENTITY> get(Collection<KEY> keys);

    ENTITY create(ENTITY entity);

    ENTITY create(ENTITY entity, InsertOption option);

    int create(List<ENTITY> entities);

    int[] insert(List<ENTITY> entities);

    int create(List<ENTITY> entities, InsertOption option);

    int[] insert(List<ENTITY> entities, InsertOption option);

    boolean update(ENTITY entity);

    int update(List<ENTITY> entities);

    List<ENTITY> findAll();

    boolean delete(ENTITY entity);

    int delete(Collection<ENTITY> entities);

    boolean delete(KEY id);

    int delete(List<KEY> ids);

}
