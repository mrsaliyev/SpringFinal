package spring.finalProject.services.impl;

import spring.finalProject.entities.models.CategoryEntity;
import spring.finalProject.entities.models.ProgrammerEntity;
import spring.finalProject.entities.models.ProjectEntity;
import spring.finalProject.entities.models.TechnologyEntity;
import spring.finalProject.repositories.CategoryRepository;
import spring.finalProject.repositories.ProgrammerRepository;
import spring.finalProject.repositories.ProjectRepository;
import spring.finalProject.repositories.TechnologyRepository;
import spring.finalProject.services.ProjectService;
import spring.finalProject.services.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
//@EnableWebSecurity
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProgrammerRepository programmerRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private ProjectStatusService projectStatusService;

    @Override
    public void uploadImgFromController(Long projectId, MultipartFile file) {
        if (Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(), "image/png")) {
            try {
                ProjectEntity project = this.getProjectById(projectId).get();

                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                project.setImgPath((fileName));
                ProjectEntity savedProject = this.save(project);
                String uploadDir = "./project-img/" + savedProject.getId();
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    System.out.println(filePath.toFile().getAbsolutePath());
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new IOException("Upload failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addFromController(String title, String shortDescription, Long statusId, String body, String footer) {
        ProjectEntity project = new ProjectEntity();
        project.setTitle(title);
        project.setShortDescription(shortDescription);
        project.setLikeAmount(0);
        project.setStatus(projectStatusService.getProjectStatusById(statusId));

        if (body != null) {
            project.setBody(body);
        }
        if (footer != null) {
            project.setFooter(footer);
        }
        this.add(project);
    }

    @Override
    public void updateFromController(Long id, String title, String shortDescription, Long statusId, String body, String footer, String img_path, Boolean isIFrame) {
        Optional<ProjectEntity> project = this.getProjectById(id);
        if (project.isPresent()) {
            ProjectEntity projectEntity = project.get();
            projectEntity.setTitle(title);
            projectEntity.setShortDescription(shortDescription);
//            if (img_path != null)
//                projectEntity.setImgPath(img_path);
            projectEntity.setStatus(projectStatusService.getProjectStatusById(statusId));
            projectEntity.setUpdatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            projectEntity.setIFrame(isIFrame);
            if (body != null) {
                projectEntity.setBody(body);
            }
            if (footer != null) {
                projectEntity.setFooter(footer);
            }
            this.save(projectEntity);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        Optional<ProjectEntity> project = this.getProjectById(id);
        project.ifPresent(this::delete);
    }

    @Override
    public ProjectEntity save(ProjectEntity project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectEntity add(ProjectEntity project) {
        return projectRepository.save(project);
    }

    @Override
    public void delete(ProjectEntity project) {
        projectRepository.delete(project);
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<ProjectEntity> getTopProjects(int amount) {
        Pageable limit = PageRequest.of(0, amount);
        return projectRepository.getTopProjects(limit);
    }

    @Override
    public List<ProjectEntity> getTopVisitProjects(int amount) {
        Pageable limit = PageRequest.of(0, amount);
        return projectRepository.getTopVisitProjects(limit);
    }

    @Override
    public List<ProjectEntity> getNewsProjects(int amount) {
        Pageable limit = PageRequest.of(0, amount);
        return projectRepository.getNewsProjects(limit);
    }

    @Override
    public List<ProjectEntity> getTopProjectsByCategory(int amount) {
        return null;
    }

    @Override
    public List<ProjectEntity> getLatestProjects(int amount) {
        Pageable limit = PageRequest.of(0, amount);
        return projectRepository.getLatestProjects(limit);
    }

    @Override
    public List<ProjectEntity> getLatestProjectsByCategory(int amount) {
        return null;
    }

    @Override
    public List<ProjectEntity> getLatestProjectByTitle(String title) {
        return projectRepository.findAllByTitleContaining(title);
    }

    @Override
    public Page<ProjectEntity> getProjectsByCategory(long categoryId, int page, int size) {
        Pageable limit = PageRequest.of(page, size);
        return projectRepository.getProjectByCategory(limit, categoryId);
    }

    @Override
    public boolean modifyLike(long projectId, boolean isLike) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            if (isLike) {
                project.likeInc();
            } else {
                project.likeDec();
            }
            projectRepository.save(project);
            return true;
        }

        return false;
    }

    @Override
    public void saveProject(ProjectEntity project) {
        projectRepository.save(project);
    }

    @Override
    public Page<ProjectEntity> searchProjects(String q, int page, int size) {
        Pageable limit = PageRequest.of(page, size);
        return projectRepository.searchProjects(limit, q.toLowerCase(Locale.ROOT));
    }

    @Override
    public Optional<ProjectEntity> getProjectById(long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void deleteCategory(long project_id, long category_id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(project_id);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            project.deleteCategory(category_id);
            projectRepository.save(project);
        }
    }

    @Override
    public void addCategory(long project_id, long category_id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(project_id);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            Optional<CategoryEntity> categoryOptional= categoryRepository.findById(category_id);
            if (categoryOptional.isPresent()){
                project.addCategory(categoryOptional.get());
                projectRepository.save(project);
            }
        }
    }

    @Override
    public void deleteTechnology(long project_id, long technology_id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(project_id);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            project.deleteTechnology(technology_id);
            projectRepository.save(project);
        }
    }

    @Override
    public void addTechnology(long project_id, long technology_id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(project_id);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            Optional<TechnologyEntity> technologyOptional= technologyRepository.findById(technology_id);
            if (technologyOptional.isPresent()){
                project.addTechnology(technologyOptional.get());
                projectRepository.save(project);
            }
        }
    }

    @Override
    public void deleteProgrammer(long project_id, long programmer_id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(project_id);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            project.deleteProgrammer(programmer_id);
            projectRepository.save(project);
        }
    }

    @Override
    public void addProgrammer(long project_id, long programmer_id) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(project_id);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            Optional<ProgrammerEntity> programmerOptional= programmerRepository.findById(programmer_id);
            if (programmerOptional.isPresent()){
                project.addProgrammer(programmerOptional.get());
                projectRepository.save(project);
            }
        }
    }

}
