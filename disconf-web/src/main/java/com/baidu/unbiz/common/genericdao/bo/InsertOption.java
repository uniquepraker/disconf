package com.baidu.unbiz.common.genericdao.bo;

/**
 * Created by praker on 05/01/2018.
 */
public enum InsertOption {

    LOW_PRIORITY, DELAYED, HIGH_PRIORITY, IGNORE;

    public String toString() {
        return this.name().toLowerCase();
    }

}
