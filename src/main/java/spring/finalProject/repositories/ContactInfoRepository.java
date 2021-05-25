package spring.finalProject.repositories;

import spring.finalProject.entities.models.ContactInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfoEntity, Long> {
}
