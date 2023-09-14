package com.cmu.base.domaine;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseLibDesc extends BaseLib implements ILibDesc {
    
    @Column(length = 2048)
    private String description;

    public BaseLibDesc() {
    }
 
    public BaseLibDesc(String lib) {
        super(lib);
    }
 
    public BaseLibDesc(String lib, String description) {
        super(lib);
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
