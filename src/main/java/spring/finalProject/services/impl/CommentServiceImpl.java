package spring.finalProject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.finalProject.entities.models.CommentEntity;
import spring.finalProject.repositories.CommentRepository;
import spring.finalProject.services.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;

    @Override
    public CommentEntity add(CommentEntity comment) {
        return repository.save(comment);
    }

    @Override
    public CommentEntity save(CommentEntity comment) {
        return repository.save(comment);
    }

    @Override
    public void delete(CommentEntity comment) {
        repository.delete(comment);
    }

    @Override
    public List<CommentEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public CommentEntity getOneById(Long id) {
        return repository.getOne(id);
    }
}
