package spring.finalProject.services;

import spring.finalProject.entities.models.TechnologyEntity;

import java.util.List;

public interface TechnologyService {
    void addFromController(String name, String icon);
    void updateFromController(Long id, String name, String icon);
    void deleteFromController(Long id);

    TechnologyEntity add(TechnologyEntity technology);
    TechnologyEntity save(TechnologyEntity technology);
    void delete(TechnologyEntity technology);
    List<TechnologyEntity> getAllTechnologies();
    TechnologyEntity getTechnologyById(Long id);
}
