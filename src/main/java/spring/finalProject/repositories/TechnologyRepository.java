package spring.finalProject.repositories;

import spring.finalProject.entities.models.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<TechnologyEntity, Long> {
}
