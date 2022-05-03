package com.goku.infraestructure.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
public abstract class BaseEntity implements Serializable {

    @Column(name = "NUM_VERSION", nullable = false)
    @Version
    private Long version;

    @Column(name = "FLG_ACTIVE", nullable = false)
    private Boolean active = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DAT_CREATION")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DAT_UPDATE")
    private Date updatedAt;

    /**
     * Sets createdAt before insert
     */
    @PrePersist
    public void setCreationDate() {
        this.createdAt = new Date();
    }

    /**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = new Date();
    }

    public void updateActive(Boolean activeReq) {
        active = activeReq;
    }

}
