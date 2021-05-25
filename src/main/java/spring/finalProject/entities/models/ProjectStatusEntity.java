package spring.finalProject.entities.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "project_status")
public class ProjectStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @OneToMany(targetEntity = ProjectEntity.class, mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectEntity> projects;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
