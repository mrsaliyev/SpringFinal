package spring.finalProject.services;

import spring.finalProject.entities.models.SkillEntity;

import java.util.List;

public interface SkillService {
    void addFromController(String name, String icon, String description);
    void updateFromController(Long id, String name, String icon, String description);
    void deleteFromController(Long id);

    SkillEntity add(SkillEntity skill);
    SkillEntity save(SkillEntity skill);
    void delete(SkillEntity skill);
    List<SkillEntity> getAllSkills();
//    List<SkillEntity> getSkillsByProgrammer(ProgrammerEntity programmer);
    SkillEntity getSkillById(Long id);
}
