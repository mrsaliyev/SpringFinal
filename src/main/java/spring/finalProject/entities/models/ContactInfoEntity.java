package spring.finalProject.entities.models;

import spring.finalProject.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "contact_info")
public class ContactInfoEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String contact;

    @ManyToOne()
    @JoinColumn(name = "programmer_id", referencedColumnName = "id")
    private ProgrammerEntity programmer;

    public ContactInfoEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ProgrammerEntity getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerEntity programmer) {
        this.programmer = programmer;
    }
}
