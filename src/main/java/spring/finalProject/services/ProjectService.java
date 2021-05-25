package spring.finalProject.services;

import spring.finalProject.entities.models.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void uploadImgFromController(Long projectId, MultipartFile file);
    void addFromController(String title, String shortDescription, Long statusId, String body, String footer);
    void updateFromController(Long id, String title, String shortDescription, Long statusId, String body, String footer, String img_path, Boolean isIFrame);
    void deleteFromController(Long id);

    ProjectEntity add(ProjectEntity project);
    ProjectEntity save(ProjectEntity project);
    void delete(ProjectEntity project);
    List<ProjectEntity> getAllProjects();
    List<ProjectEntity> getTopProjects(int amount);
    List<ProjectEntity> getTopVisitProjects(int amount);
    List<ProjectEntity> getNewsProjects(int amount);
    List<ProjectEntity> getTopProjectsByCategory(int amount);
    List<ProjectEntity> getLatestProjects(int amount);
    List<ProjectEntity> getLatestProjectsByCategory(int amount);
    List<ProjectEntity> getLatestProjectByTitle(String title);
    Page<ProjectEntity> getProjectsByCategory(long categoryId, int page, int size);
    boolean modifyLike(long projectId, boolean isLike);
    void saveProject(ProjectEntity project);
    Page<ProjectEntity> searchProjects(String q, int page, int size);
    Optional<ProjectEntity> getProjectById(long id);
    void deleteCategory(long project_id, long category_id);
    void addCategory(long project_id, long category_id);
    void deleteTechnology(long project_id, long technology_id);
    void addTechnology(long project_id, long technology_id);
    void deleteProgrammer(long project_id, long programmer_id);
    void addProgrammer(long project_id, long programmer_id);
}
