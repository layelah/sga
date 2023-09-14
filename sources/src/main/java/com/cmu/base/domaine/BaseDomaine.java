package com.cmu.base.domaine;

import com.cmu.util.JIDate;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseDomaine implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public Integer id;
    
    @Column(name = "insert_date", nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;

    @Column(name = "insert_user_id", nullable = false, updatable = false)
    private int insertUserId;
    
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    @Column(name = "update_user_id", insertable = false)
    private Integer updateUserId;
    
    @Version
    private int version = 0;
   
    public BaseDomaine() {
        this.id = 0;
    }

 
    public static Integer getID(BaseDomaine obj) {
        if (obj != null) {
            return obj.getId();
        } else {
            return null;
        }
    }
    
    public static boolean idIsNull(BaseDomaine obj) {
        return (obj == null || idIsNull(obj.getId()));
    }
    
    public static boolean idIsNull(Integer id) {
        return ((id == null) || (id == 0));
    }
    
    public boolean avecProprieteEstSupprime(){
        return avecPropriete("estSupprime");
    }
    
    public boolean avecProprieteEstCache(){
        return avecPropriete("estCache");
    }
    
    public boolean avecProprieteEntite() {
        return avecPropriete("entite");
    }
    
    private boolean avecPropriete(String propName) {
        Class aClass = this.getClass();
        Field[] fields = aClass.getFields();
        
        for (Field field: fields) {
            if (propName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }
 
    public boolean estCreateur(Integer userId) {
        return (userId != 0);
    }
    
    public String getLib() {
        return "LIB ["+id.toString() +"]";
    }
    
    public String getNomClasse() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public final int hashCode() {
        int hash = 0;
        //hash += (id != null ? id.hashCode() * getNombrePremierPourHashCode() : 0);
        hash += (id != null ? id.hashCode() + this.getClass().hashCode() : 0);
        return hash;
    }

    @Override
    public final boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaseDomaine)) {
            return false;
        }
        BaseDomaine other = (BaseDomaine) object;
        if (!(this.getClass().equals(object.getClass()))
                || (this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return this.getClass().getName() + " [ id=" + id + ", adr = " + super.toString() + "]";
    }
    
    @PreUpdate
    public void update() {
        updateDate = JIDate.dateHeure();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(int insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
