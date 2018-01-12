package com.baidu.disconf.web.service.sign.form;

import com.baidu.dsp.common.form.RequestFormBase;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by praker on 04/01/2018.
 */
public class SigninForm extends RequestFormBase {

    private static final long serialVersionUID = 565717265045704403L;


    @NotNull(message = "name.empty")
    @NotEmpty(message = "name.empty")
    private String name;
    public static final String NAME = "name";

    @NotNull(message = "password.empty")
    @NotEmpty(message = "password.empty")
    private String password;
    public static final String PASSWORD = "password";


    @NotNull
    @Range(min = 0, max = 1)
    private Integer remember;
    public static final String REMEMBER = "remember";



    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public Integer getRemember() {
        return remember;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemember(Integer remember) {
        this.remember = remember;
    }

}
