package spring.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.finalProject.entities.models.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
