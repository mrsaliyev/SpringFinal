package spring.finalProject.repositories;

import spring.finalProject.entities.models.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAllByOrderByCreatedAtDesc();
    List<ProjectEntity> findAllByTitleContaining(String title);

    @Query("SELECT DISTINCT proj FROM projects proj left join proj.programmers left join proj.category " +
            "WHERE proj.status.status = 'Done' AND proj.deletedAt IS NULL " +
            "ORDER BY proj.createdAt DESC")
    List<ProjectEntity> getLatestProjects(Pageable limit);


    @Query("SELECT DISTINCT proj FROM projects proj left join proj.programmers left join proj.category " +
            "WHERE proj.status.status = 'Done' AND proj.deletedAt IS NULL " +
            "ORDER BY proj.likeAmount DESC")
    List<ProjectEntity> getTopProjects(Pageable limit);

    @Query("SELECT DISTINCT proj FROM projects proj left join proj.programmers left join proj.category " +
            "WHERE proj.status.status = 'Done' AND proj.deletedAt IS NULL " +
            "ORDER BY proj.visitAmount DESC")
    List<ProjectEntity> getTopVisitProjects(Pageable limit);

    @Query("SELECT DISTINCT proj FROM projects proj left join proj.programmers left join proj.category cat " +
            "WHERE cat.code = 'NEWS' AND proj.status.status = 'Done' " +
            "ORDER BY proj.createdAt DESC")
    List<ProjectEntity> getNewsProjects(Pageable limit);

    @Query("SELECT DISTINCT proj FROM projects proj left join proj.category category " +
            "WHERE category.id = :categoryId AND proj.status.status = 'Done' AND proj.deletedAt IS NULL " +
            "ORDER BY proj.createdAt DESC")
    Page<ProjectEntity> getProjectByCategory(Pageable limit, @Param("categoryId") long id);

    @Query("SELECT DISTINCT proj FROM projects proj WHERE (lower(proj.shortDescription) LIKE %:q% OR lower(proj.title) LIKE %:q%) " +
            "AND proj.status.status = 'Done' AND proj.deletedAt IS NULL ")
    Page<ProjectEntity> searchProjects(Pageable limit, @Param("q") String q);
}
