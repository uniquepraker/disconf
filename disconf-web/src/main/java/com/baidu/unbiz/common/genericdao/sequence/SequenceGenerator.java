package com.baidu.unbiz.common.genericdao.sequence;

/**
 * Created by praker on 05/01/2018.
 */
public interface SequenceGenerator {

    /**
     * @param sequenceName
     *
     * @return 上午11:27:22 created by Darwin(Tianxin)
     */
    Integer getIntKey(String sequenceName);

    /**
     * @param sequenceName
     *
     * @return 上午11:27:34 created by Darwin(Tianxin)
     */
    Long getKey(String sequenceName);

    /**
     * @param sequenceName
     * @param size         上午11:30:02 created by Darwin(Tianxin)
     */
    void initialSequence(String sequenceName, int size);

}
