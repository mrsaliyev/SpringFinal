package spring.finalProject.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.finalProject.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "projects")
@Indexed
@Data
@AllArgsConstructor
public class ProjectEntity extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "img_path", columnDefinition = "TEXT")
    private String imgPath;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "footer", columnDefinition = "TEXT")
    private String footer;

    @Column(name = "like_amount")
    private Integer likeAmount = 0;

    @Column(name = "isIFrame")
    private Boolean isIFrame = false;

    @Column(name = "visit_amount")
    private Integer visitAmount = 0;

    @ManyToMany
    @JoinTable(
            name = "programmer_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "programmer_id"))
    @JsonBackReference
    private List<ProgrammerEntity> programmers;

    @ManyToOne()
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private ProjectStatusEntity status;

    @ManyToMany
    @JoinTable(
            name = "category_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    private List<CategoryEntity> category;

    @ManyToMany
    @JoinTable(
            name = "technology_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
//    @JsonBackReference
    private List<TechnologyEntity> technologies;

    @OneToMany(targetEntity = CommentEntity.class)
    private List<CommentEntity> comments = new ArrayList<>();

    public ProjectEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public List<ProgrammerEntity> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(List<ProgrammerEntity> programmers) {
        this.programmers = programmers;
    }

    public ProjectStatusEntity getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEntity status) {
        this.status = status;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
    }

    public List<TechnologyEntity> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyEntity> technologies) {
        this.technologies = technologies;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Transient
    public String getImgPathDirectory() {
        if (imgPath != null)
            return "/project-img/" + this.getId() + "/" + imgPath;
        return null;
    }

    public Boolean getIsIFrame() {
        return isIFrame;
    }

    public void setIsIFrame(Boolean IFrame) {
        isIFrame = IFrame;
    }

    public Boolean getIFrame() {
        return isIFrame;
    }

    public void setIFrame(Boolean IFrame) {
        isIFrame = IFrame;
    }

    public Integer getVisitAmount() {
        return visitAmount;
    }

    public void setVisitAmount(Integer visitAmount) {
        this.visitAmount = visitAmount;
    }

    public void visitInc() {
        visitAmount++;
    }

    public void likeInc() {
        likeAmount++;
    }

    public void likeDec() {
        if (likeAmount > 0) likeAmount--;
    }

    public void addCategory(CategoryEntity cat) {
        if (!category.contains(cat)) category.add(cat);
    }

    public void deleteCategory(Long cat) {
        category.removeIf(categoryEntity -> categoryEntity.getId().equals(cat));
    }

    public void addProgrammer(ProgrammerEntity programmer) {
        if (!programmers.contains(programmer)) programmers.add(programmer);
    }

    public void deleteProgrammer(Long programmer_id) {
        programmers.removeIf(programmerEntity -> programmerEntity.getId().equals(programmer_id));
    }

    public void addTechnology(TechnologyEntity technology) {
        if (!technologies.contains(technology)) technologies.add(technology);
    }

    public void deleteTechnology(Long technology_id) {
        technologies.removeIf(technologyEntity -> technologyEntity.getId().equals(technology_id));
    }
}
