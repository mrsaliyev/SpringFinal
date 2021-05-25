package spring.finalProject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = java.sql.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = new Date();
    }

    @PreRemove
    public void preDelete(){
        this.deletedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}