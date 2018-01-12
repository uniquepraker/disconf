package com.baidu.unbiz.common.genericdao.mapper;

import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.baidu.unbiz.common.genericdao.annotation.Table.ColumnStyle;
import com.baidu.unbiz.common.genericdao.util.ClassUtils;
import com.github.knightliao.apollo.db.bo.BaseObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by praker on 05/01/2018.
 */
public class MappingItem {

    private String dbColumn;

    private Boolean modifiable;

    private Field field;

    private Method getter;

    private Method setter;


    public String getDbColumn() {
        return dbColumn;
    }

    public Boolean getModifiable() {
        return modifiable;
    }

    public Field getField() {
        return field;
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }


    public void setDbColumn(String dbColumn) {
        this.dbColumn = dbColumn;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }


    public boolean isModifiable() {
        return modifiable;
    }



    public MappingItem(Field field, Method setterMethod, Method getterMethod, ColumnStyle columnStyle) {
        this(field, setterMethod, getterMethod, columnStyle, false);
    }

    public MappingItem(Field field, Method setterMethod, Method getterMethod, ColumnStyle columnStyle, boolean columnsModified) {

        this.field = field;
        this.getter = getterMethod;
        this.setter = setterMethod;

        Column column = getColumnAnnotation(field, setter,getter);

        if (column == null) {
            this.dbColumn = columnStyle.convert(field.getName());
            modifiable = columnsModified;
        } else if (column.value().equals("ignore") || column.ignore()) {

        } else {
            this.dbColumn = column.value();
            modifiable = column.maybeModified();
        }
    }


    static Column getColumnAnnotation(Field f, Method set, Method get) {

        Column column = f.getAnnotation(Column.class);

        Column gColumn = get.getAnnotation(Column.class);
        Column sColumn = set.getAnnotation(Column.class);

        Class<?> sClass = set.getDeclaringClass();
        Class<?> gClass = get.getDeclaringClass();

        if (gColumn != null && !gClass.isAssignableFrom(sClass)) {
            return gColumn;
        }

        if (sColumn != null && !sClass.isAssignableFrom(gClass)) {
            return sColumn;
        }

        return column;
    }


    public static List<MappingItem> getMappingItems(Class<?> clazz) {

        if (!ClassUtils.isBaiduClass(clazz)) {
            return new ArrayList<MappingItem>(0);
        }

        // 如果不是BaseObject的子類，則一定是keyClass
        boolean isKeyClass = !BaseObject.class.isAssignableFrom(clazz);

        List<MappingItem> mappingItems = new ArrayList<MappingItem>(32);

        Set<Field> fields = ClassUtils.getAllFiled(clazz);
        Set<Method> methods = ClassUtils.getAllMethod(clazz);
        Map<String, Method> methodMap = ClassUtils.filter2Map(methods);

        Table table = isKeyClass ? null : clazz.getAnnotation(Table.class);
        String keyColumn = isKeyClass ? null : table.keyColumn();

        // 循环处理所有字段，过滤出该类加载为对象时需要调用的setter方法map
        for (Field f : fields) {
            // 静态字段则自动pass
            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }

            // 不做关联加载的工作
            Class<?> fType = f.getType();
            boolean isBaiduClass = ClassUtils.isBaiduClass(fType);
            if (isBaiduClass || Collection.class.isAssignableFrom(fType) || fType.isArray()) {
                continue;
            }

            // 字段名字
            String name = f.getName().toLowerCase();
            boolean isKey = name.equals("id");
            if (isKey && isBaiduClass) {
                continue;
            }

            // 其他字段获取field，getter，setter
            Method set = methodMap.get("set" + name);
            Method get = methodMap.get("get" + name);
            if (get == null) {
                get = methodMap.get("is" + name);
            }
            // FIXME
            MappingItem item =
                    new MappingItem(f, set, get, (table != null) ? table.columnStyle() : ColumnStyle.LOWER_CASE,
                            (table != null) ? table.columnsModified() : false);
            if (item.isIgnore()) {
                continue;
            }
            item.dbColumn = isKey ? keyColumn : item.dbColumn;
            mappingItems.add(item);
        }
        return mappingItems;
    }

    private boolean isIgnore() {
        return dbColumn == null;
    }



}
