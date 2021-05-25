package spring.finalProject.services;

import spring.finalProject.entities.models.CommentEntity;

import java.util.List;

public interface CommentService {
    CommentEntity add(CommentEntity comment);
    CommentEntity save(CommentEntity comment);
    void delete(CommentEntity comment);
    List<CommentEntity> getAll();
    CommentEntity getOneById(Long id);
}
