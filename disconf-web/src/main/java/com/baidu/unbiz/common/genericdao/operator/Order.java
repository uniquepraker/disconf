package com.baidu.unbiz.common.genericdao.operator;

/**
 * Created by praker on 05/01/2018.
 */
public class Order {
    private String column;

    private boolean asc;

    /**
     * @param column
     * @param asc
     */
    public Order(String column, boolean asc) {
        this.column = column;
        this.asc = asc;
    }

    public boolean isAsc() {
        return asc;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return asc;
    }
}
