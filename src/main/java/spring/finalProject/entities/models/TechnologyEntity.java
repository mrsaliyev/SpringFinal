package spring.finalProject.entities.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "technology")
public class TechnologyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String technology;

    @Column(length = 2048)
    private String icon;

    @ManyToMany(mappedBy = "technologies")
    private List<ProjectEntity> projects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
