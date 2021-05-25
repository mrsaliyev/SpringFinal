package spring.finalProject.services.impl;

import spring.finalProject.entities.models.ProjectStatusEntity;
import spring.finalProject.repositories.ProjectStatusRepository;
import spring.finalProject.services.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {
    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Override
    public void addFromController(String name) {
        ProjectStatusEntity status = new ProjectStatusEntity();
        status.setStatus(name);
        this.add(status);
    }

    @Override
    public void updateFromController(Long id, String name) {
        ProjectStatusEntity status = this.getProjectStatusById(id);
        if (status != null) {
            status.setStatus(name);
            this.save(status);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        ProjectStatusEntity status = this.getProjectStatusById(id);
        if (status != null) {
            this.delete(status);
        }
    }

    @Override
    public ProjectStatusEntity getProjectStatusById(long id) {
        return projectStatusRepository.getOne(id);
    }

    @Override
    public ProjectStatusEntity add(ProjectStatusEntity projectStatus) {
        return projectStatusRepository.save(projectStatus);
    }

    @Override
    public ProjectStatusEntity save(ProjectStatusEntity projectStatus) {
        return projectStatusRepository.save(projectStatus);
    }

    @Override
    public void delete(ProjectStatusEntity projectStatus) {
        projectStatusRepository.delete(projectStatus);
    }

    @Override
    public List<ProjectStatusEntity> getAllProjectStatuses() {
        return projectStatusRepository.findAll();
    }
}
