package spring.finalProject.services.impl;

import spring.finalProject.entities.models.SkillEntity;
import spring.finalProject.repositories.SkillRepository;
import spring.finalProject.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void addFromController(String name, String icon, String description) {
        SkillEntity skill = new SkillEntity();
        skill.setSkill(name);
        if (icon != null)
            skill.setSkillIcon(icon);
        if (description != null)
            skill.setSkillDescription(description);
        this.add(skill);
    }

    @Override
    public void updateFromController(Long id, String name, String icon, String description) {
        SkillEntity skill = this.getSkillById(id);
        if (skill != null) {
            skill.setSkill(name);
            if (icon != null)
                skill.setSkillIcon(icon);
            if (description != null)
                skill.setSkillDescription(description);
            skill.setUpdatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            this.save(skill);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        SkillEntity skill = this.getSkillById(id);
        if (skill != null) {
            this.delete(skill);
        }
    }

    @Override
    public SkillEntity add(SkillEntity skill) {
        return skillRepository.save(skill);
    }

    @Override
    public SkillEntity save(SkillEntity skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void delete(SkillEntity skill) {
        skillRepository.delete(skill);
    }

    @Override
    public List<SkillEntity> getAllSkills() {
        return skillRepository.findAll();
    }

//    @Override
//    public List<SkillEntity> getSkillsByProgrammer(ProgrammerEntity programmer) {
//        return skillRepository.findAllByProgrammer(programmer);
//    }

    @Override
    public SkillEntity getSkillById(Long id) {
        return skillRepository.getOne(id);
    }
}
