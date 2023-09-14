package com.cmu.base.domaine;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseLibCode extends BaseLib implements ILibCode {
    
    @Column(nullable = false, length = 20)
    private String code;

    public BaseLibCode() {
    }

    public BaseLibCode(String lib, String code) {
        super(lib);
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
    
    public String getLibCode() {
        return (code != null ? code +" - "+ getLib() : "");
    }
}
