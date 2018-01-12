package com.baidu.unbiz.common.genericdao.mapper;

import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;
import org.apache.commons.lang3.StringUtils;

import javax.naming.ldap.PagedResultsControl;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by praker on 05/01/2018.
 */
public class ORMapping<ENTITY extends BaseObject<K>, K extends Serializable> {

    private String table = null;

    private int shardCount = 0;

    private Class<K> keyClass = null;

    private Class<ENTITY> entityClass = null;

    private String sKeyColumn = null;

    private Set<String> keyColumn = new HashSet<String>();

    private Set<String> allColumns = new HashSet<String>();

    private Set<String> modifiableColumns = new HashSet<String>();

    private Map<String, MappingItem> columnMethodMap = new HashMap<String, MappingItem>();

    public ORMapping(Class<ENTITY> entityClass, Class<K> keyClass) {
        this.keyClass = keyClass;
        this.entityClass = entityClass;
        initMapping();
    }

    private void initMapping() {
        Table table = entityClass.getAnnotation(Table.class);

        this.sKeyColumn = table.keyColumn();
        this.keyColumn.addAll(Arrays.asList(sKeyColumn.split(",")));

        this.table =
                (table.name().equals("")) ? table.columnStyle().convert(entityClass.getSimpleName()) : table.name();
        this.table = (StringUtils.isNotBlank(table.db())) ? table.db() + "." + this.table : this.table;

        this.shardCount = table.shardCount();

        List<MappingItem> mappingItems = MappingItem.getMappingItems(entityClass);
        List<MappingItem> keyMappings = MappingItem.getMappingItems(keyClass);
        mappingItems.addAll(keyMappings);

        for (MappingItem item : mappingItems) {

            if (item.getDbColumn().indexOf(',') >= 0) {
                continue;
            }
            columnMethodMap.put(item.getDbColumn(), item);
            if (item.isModifiable()) {
                modifiableColumns.add(item.getDbColumn());
            }
        }

        allColumns.addAll(columnMethodMap.keySet());

    }


    /**
     * @return the allColumns
     */
    public Set<String> getAllColumns() {
        return allColumns;
    }

    /**
     * @return the keyColumn
     */
    public Set<String> getKeyColumn() {
        return keyColumn;
    }

    /**
     * @return the modifiableColumns
     */
    public Set<String> getModifiableColumns() {
        return modifiableColumns;
    }

    /**
     * @return the columnMethodMap
     */
    public Map<String, MappingItem> getColumnMethodMap() {
        return columnMethodMap;
    }

    /**
     */
    public boolean isComplexKey() {
        return keyColumn.size() > 1;
    }

    public MappingItem getMethodPair(String column) {
        MappingItem methods = columnMethodMap.get(column);
        if (methods == null) {
            column = column.toLowerCase();
            methods = columnMethodMap.get(column);
        }
        if (methods == null) {
            column = column.replaceAll("_", "");
            methods = columnMethodMap.get(column);
        }
        return methods;
    }

    /**
     * 獲取某字段對應的get方法
     *
     * @param column
     */
    public Method getGetter(String column) {
        MappingItem methods = getMethodPair(column);
        return methods == null ? null : methods.getGetter();
    }

    /**
     * 獲取某字段對應的get方法
     *
     * @param column
     */
    public Method getSetter(String column) {
        MappingItem methods = getMethodPair(column);
        return methods == null ? null : methods.getSetter();
    }

    /**
     * @param column
     */
    public boolean isKeyColumn(String column) {
        return keyColumn.contains(column);
    }

    /**
     * @return the keyClass
     */
    public Class<K> getKeyClass() {
        return keyClass;
    }

    /**
     * @return the entityClass
     */
    public Class<ENTITY> getEntityClass() {
        return entityClass;
    }

    /**
     * @return 下午3:01:27 created by Darwin(Tianxin)
     */
    public String getSKeyColumn() {
        return sKeyColumn;
    }

    /**
     * @return 下午3:07:23 created by Darwin(Tianxin)
     */
    public String getTable() {
        return this.table;
    }

    public int getShardCount() {
        return this.shardCount;
    }


}
