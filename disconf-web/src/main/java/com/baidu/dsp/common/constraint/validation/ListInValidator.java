package com.baidu.dsp.common.constraint.validation;

import com.baidu.dsp.common.constraint.ListInConstraint;
import com.github.knightliao.apollo.utils.common.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListInValidator implements ConstraintValidator<ListInConstraint, Integer> {

    private String allowIntegerListStr;
    private List<Integer> allowIntegerList;

    private static final String SEP = ",";

    public void initialize(ListInConstraint constraintAnnotation) {

        this.allowIntegerListStr = constraintAnnotation.allowIntegerList();

        allowIntegerList = StringUtil.parseStringToIntegerList(allowIntegerListStr, SEP);
    }

    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        return allowIntegerList.contains(value);
    }
}
