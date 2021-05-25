package spring.finalProject.entities.models;

import spring.finalProject.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "category")
public class CategoryEntity extends BaseEntity {

    @ManyToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ProjectEntity> project;

    @Column
    private String category;

    @Column
    private String code;

    @Column(name = "img_path")
    private String imgPath;

    public List<ProjectEntity> getProject() {
        return project;
    }

    public void setProject(List<ProjectEntity> project) {
        this.project = project;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
