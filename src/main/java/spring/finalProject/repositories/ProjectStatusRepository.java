package spring.finalProject.repositories;

import spring.finalProject.entities.models.ProjectStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatusEntity, Long> {
}
