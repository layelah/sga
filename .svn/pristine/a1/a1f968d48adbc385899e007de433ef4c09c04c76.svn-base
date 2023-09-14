package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseDomaine;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "table_valeur_lib")
@NamedQueries({
    @NamedQuery(name = "TableValeurLib.findAll", query = "SELECT t FROM TableValeurLib t")})
public class TableValeurLib extends BaseDomaine {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String lib1;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "datatype1_code")
    private String datatype1Code;
    
    @Size(max = 100)
    private String lib2;
    
    @Size(max = 20)
    @Column(name = "datatype2_code")
    private String datatype2Code;
    
    @Size(max = 100)
    private String lib3;
    
    @Size(max = 20)
    @Column(name = "datatype3_code")
    private String datatype3Code;
    
    @Size(max = 100)
    private String lib4;
    
    @Size(max = 20)
    @Column(name = "datatype4_code")
    private String datatype4Code;
    
    @Size(max = 100)
    private String lib5;
    
    @Size(max = 20)
    @Column(name = "datatype5_code")
    private String datatype5Code;
    
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TableValeurType type;

    public TableValeurLib() {
    }

    public String getLib1() {
        return lib1;
    }

    public void setLib1(String lib1) {
        this.lib1 = lib1;
    }

    public String getDatatype1Code() {
        return datatype1Code;
    }

    public void setDatatype1Code(String datatype1Code) {
        this.datatype1Code = datatype1Code;
    }

    public String getLib2() {
        return lib2;
    }

    public void setLib2(String lib2) {
        this.lib2 = lib2;
    }

    public String getDatatype2Code() {
        return datatype2Code;
    }

    public void setDatatype2Code(String datatype2Code) {
        this.datatype2Code = datatype2Code;
    }

    public String getLib3() {
        return lib3;
    }

    public void setLib3(String lib3) {
        this.lib3 = lib3;
    }

    public String getDatatype3Code() {
        return datatype3Code;
    }

    public void setDatatype3Code(String datatype3Code) {
        this.datatype3Code = datatype3Code;
    }

    public String getLib4() {
        return lib4;
    }

    public void setLib4(String lib4) {
        this.lib4 = lib4;
    }

    public String getDatatype4Code() {
        return datatype4Code;
    }

    public void setDatatype4Code(String datatype4Code) {
        this.datatype4Code = datatype4Code;
    }

    public String getLib5() {
        return lib5;
    }

    public void setLib5(String lib5) {
        this.lib5 = lib5;
    }

    public String getDatatype5Code() {
        return datatype5Code;
    }

    public void setDatatype5Code(String datatype5Code) {
        this.datatype5Code = datatype5Code;
    }

    public TableValeurType getType() {
        return type;
    }

    public void setTypeId(TableValeurType type) {
        this.type = type;
    }
}
