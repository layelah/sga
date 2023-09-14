package com.cmu.base.domaine;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class BaseLib extends BaseDomaine implements ILib{
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib", nullable = false, length = 100)
    private String lib;

    public BaseLib() {
        super();
    }
    
    public BaseLib(String lib) {
        super();
        this.lib = lib;
    }
    
    @Override
    public String getLib() {
        return lib;
    }

    @Override
    public void setLib(String lib) {
        this.lib = lib;
    }
}
