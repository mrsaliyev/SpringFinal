package spring.finalProject.entities.models;

import spring.finalProject.entities.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "programmers")
public class ProgrammerEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "profile_img", columnDefinition = "TEXT")
    private String profile_img;

    @OneToMany(targetEntity = ContactInfoEntity.class, mappedBy = "programmer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContactInfoEntity> contactsInfo;

//    @OneToMany(targetEntity = SkillEntity.class, mappedBy = "programmer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<SkillEntity> skills;

    @ManyToMany(mappedBy = "programmers")
    private List<ProjectEntity> projects;

    @OneToOne(mappedBy = "programmer")
    private Users user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ContactInfoEntity> getContactsInfo() {
        return contactsInfo;
    }

    public void setContactsInfo(List<ContactInfoEntity> contactsInfo) {
        this.contactsInfo = contactsInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

//    @Transient
//    public String getProfileImgPath() {
//        if (profile_img == null)
//            return "/avatars/default.jpg";
//        return "/avatars/" + this.getId() + "/" + profile_img;
//    }
}
