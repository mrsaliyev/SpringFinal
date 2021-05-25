package spring.finalProject.services;

import spring.finalProject.entities.models.ProgrammerEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProgrammerService {
    void uploadAvatarFromController(Long programmerId, MultipartFile user_avatar);

    void addFromController(String email, String name, String lastName, String description, Long userId);
    void updateFromController(Long id, String email, String name, String lastName, String description, Long userId, List<Long> skillsIds);
    void deleteFromController(Long id);

    ProgrammerEntity add(ProgrammerEntity programmer);
    ProgrammerEntity save(ProgrammerEntity programmer);
    void delete(ProgrammerEntity programmer);
    List<ProgrammerEntity> getAllProgrammer();
    ProgrammerEntity getProgrammerById(long id);
}
