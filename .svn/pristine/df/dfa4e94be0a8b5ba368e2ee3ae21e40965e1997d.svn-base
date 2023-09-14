package com.cmu.base.domaine;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseCode extends BaseDomaine implements ICode{
    

    @Column(nullable = false, length = 20)
    private String code;

    public BaseCode() {
    }

    public BaseCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
