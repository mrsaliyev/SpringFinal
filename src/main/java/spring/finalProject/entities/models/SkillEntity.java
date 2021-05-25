package spring.finalProject.entities.models;

import spring.finalProject.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "skill")
public class SkillEntity extends BaseEntity {

    @Column
    private String skillIcon;

    @Column
    private String skill;

    @Column(length = 1024)
    private String skillDescription;

//    @ManyToOne()
//    @JoinColumn(name = "programmer_id", referencedColumnName = "id")
//    private ProgrammerEntity programmer;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

//    public ProgrammerEntity getProgrammer() {
//        return programmer;
//    }

//    public void setProgrammer(ProgrammerEntity programmer) {
//        this.programmer = programmer;
//    }

    public String getSkillIcon() {
        return skillIcon;
    }

    public void setSkillIcon(String skillIcon) {
        this.skillIcon = skillIcon;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
}
