package com.cmu.base.domaine;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseDesc extends BaseDomaine implements IDesc {
    
    @Column(length = 2048)
    protected String description;

    public BaseDesc() {
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
