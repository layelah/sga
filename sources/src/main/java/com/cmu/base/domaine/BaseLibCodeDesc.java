package com.cmu.base.domaine;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseLibCodeDesc extends BaseLibCode implements ILibCodeDesc{
    
    @Column(length = 2048)
    private String description;

    public BaseLibCodeDesc() {
    }
 
    public BaseLibCodeDesc(String lib, String code) {
        super(lib, code);
    }
 
    public BaseLibCodeDesc(String lib, String code, String description) {
        super(lib, code);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
