package com.baidu.unbiz.common.genericdao.annotation;

import com.github.knightliao.apollo.utils.common.StringUtil;

import java.lang.annotation.*;

/**
 * Created by praker on 04/01/2018.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    String db();

    String name() default "";

    int shardCount() default 0;

    String keyColumn() default "id";

    boolean columnsModified() default false;


    ColumnStyle columnStyle() default ColumnStyle.LOWER_CASE;

    public static enum ColumnStyle {
        LOWER_CASE {
            public String convert(String field) {
                return field.toLowerCase();
            }
        },


        JAVA_TO_MYSQL {
            public String convert(String field) {
                return StringUtil.toLowerCaseWithUnderscores(field);
            }
        };


        public abstract String convert(String field);

    }


    boolean isView() default false;

}
