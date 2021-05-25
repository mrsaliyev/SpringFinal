package spring.finalProject.services.impl;

import spring.finalProject.entities.models.ProgrammerEntity;
import spring.finalProject.entities.models.SkillEntity;
import spring.finalProject.entities.models.Users;
import spring.finalProject.repositories.ProgrammerRepository;
import spring.finalProject.services.ProgrammerService;
import spring.finalProject.services.SkillService;
import spring.finalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProgrammerServiceImpl implements ProgrammerService {
    @Autowired
    private ProgrammerRepository programmerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SkillService skillService;

//    @Value("${file.avatar.uploadPath}")
//    private String uploadPathAvatars;

    @Override
//    public void uploadAvatarFromController(Long programmerId, MultipartFile user_avatar) {
//        if (Objects.equals(user_avatar.getContentType(), "image/jpeg") || Objects.equals(user_avatar.getContentType(), "image/png")) {
//            try {
//                ProgrammerEntity programmer = this.getProgrammerById(programmerId);
//                String picName = DigestUtils.sha1Hex("avatar_" + programmer.getId() + "_!Picture");
//
//                byte[] bytes = user_avatar.getBytes();
//                Path path = Paths.get(uploadPathAvatars + "/" + picName + ".jpg");
//                Files.write(path, bytes);
//                programmer.setProfile_img(picName);
//                this.save(programmer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void uploadAvatarFromController(Long programmerId, MultipartFile user_avatar) {
        if (Objects.equals(user_avatar.getContentType(), "image/jpeg") || Objects.equals(user_avatar.getContentType(), "image/png")) {
            try {
                ProgrammerEntity programmer = this.getProgrammerById(programmerId);

                String fileName = StringUtils.cleanPath(Objects.requireNonNull(user_avatar.getOriginalFilename()));
                programmer.setProfile_img((fileName));
                ProgrammerEntity savedProgrammer = this.save(programmer);
                String uploadDir = "./avatars/" + savedProgrammer.getId();
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = user_avatar.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    System.out.println(filePath.toFile().getAbsolutePath());
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new IOException("Upload failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addFromController(String email, String name, String lastName, String description, Long userId) {
        ProgrammerEntity programmer = new ProgrammerEntity();
        programmer.setEmail(email);
        programmer.setName(name);
        programmer.setLast_name(lastName);
        programmer.setDescription(description);
        programmer.setCreatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (userId != null) {
            Users user = userService.getUserById(userId);
            user.setProgrammer(programmer);
            userService.saveUser(user);
        }
        this.add(programmer);
    }

    @Override
    public void updateFromController(Long id, String email, String name, String lastName, String description, Long userId, List<Long> skillsIds) {
        ProgrammerEntity programmer = this.getProgrammerById(id);
        if (programmer != null) {
            programmer.setEmail(email);
            programmer.setName(name);
            programmer.setLast_name(lastName);
            programmer.setDescription(description);
            programmer.setUpdatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            if (userId != null) {
                Users user = userService.getUserById(userId);
                user.setProgrammer(programmer);
                userService.saveUser(user);
            }

            List<SkillEntity> skills = new ArrayList<>();
            if (skillsIds != null) {
                for (int i = 0; i < skillsIds.size(); i++) {
                    skills.add(skillService.getSkillById(skillsIds.get(i)));
                }
            }
            programmer.setSkills(skills);
            this.save(programmer);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        ProgrammerEntity programmer = this.getProgrammerById(id);
        if (programmer != null) {
            this.delete(programmer);
        }
    }

    @Override
    public ProgrammerEntity add(ProgrammerEntity programmer) {
        return programmerRepository.save(programmer);
    }

    @Override
    public ProgrammerEntity save(ProgrammerEntity programmer) {
        return programmerRepository.save(programmer);
    }

    @Override
    public void delete(ProgrammerEntity programmer) {
        programmerRepository.delete(programmer);
    }

    @Override
    public List<ProgrammerEntity> getAllProgrammer() {
        return programmerRepository.findAll();
    }

    @Override
    public ProgrammerEntity getProgrammerById(long id) {
        return programmerRepository.findById(id).orElse(null);
    }
}
