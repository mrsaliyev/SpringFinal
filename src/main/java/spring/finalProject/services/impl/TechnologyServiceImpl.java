package spring.finalProject.services.impl;

import spring.finalProject.entities.models.TechnologyEntity;
import spring.finalProject.repositories.TechnologyRepository;
import spring.finalProject.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public void addFromController(String name, String icon) {
        TechnologyEntity technology = new TechnologyEntity();
        technology.setTechnology(name);
        if (icon != null)
            technology.setIcon(icon);
        this.add(technology);
    }

    @Override
    public void updateFromController(Long id, String name, String icon) {
        TechnologyEntity technology = this.getTechnologyById(id);
        if (technology != null) {
            technology.setTechnology(name);
            if (icon != null)
                technology.setIcon(icon);
            this.save(technology);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        TechnologyEntity technology = this.getTechnologyById(id);
        if (technology != null) {
            this.delete(technology);
        }
    }

    public TechnologyEntity add(TechnologyEntity technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public TechnologyEntity save(TechnologyEntity technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public void delete(TechnologyEntity technology) {
        technologyRepository.delete(technology);
    }

    @Override
    public List<TechnologyEntity> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    @Override
    public TechnologyEntity getTechnologyById(Long id) {
        return technologyRepository.getOne(id);
    }
}
