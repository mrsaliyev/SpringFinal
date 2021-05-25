package spring.finalProject.repositories;

import spring.finalProject.entities.models.ProgrammerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepository extends JpaRepository<ProgrammerEntity, Long> {
}
