package spring.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.finalProject.config.Consts;
import spring.finalProject.entities.models.*;
import spring.finalProject.services.CategoryService;
import spring.finalProject.services.ProgrammerService;
import spring.finalProject.services.ProjectService;
import spring.finalProject.services.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProgrammerService programmerService;

    @GetMapping(value = "/" + Consts.TABLE_USERS)
    public ResponseEntity<?> getUsers() {
        List<Users> users = userService.allUsers();
        return ResponseEntity.ok(Objects.requireNonNullElse(users, HttpEntity.EMPTY));
    }

    @GetMapping(value = "/" + Consts.TABLE_CATEGORIES)
    public ResponseEntity<?> getCategories() {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(Objects.requireNonNullElse(categories, HttpEntity.EMPTY));
    }

    @GetMapping(value = "/" + Consts.TABLE_PROGRAMMERS)
    public ResponseEntity<?> getProgrammers() {
        List<ProgrammerEntity> programmers = programmerService.getAllProgrammer();
        return ResponseEntity.ok(Objects.requireNonNullElse(programmers, HttpEntity.EMPTY));
    }
    @GetMapping(value = "/" + Consts.TABLE_PROGRAMMERS + "/{id}")
    public ResponseEntity<?> getProgrammerById(@PathVariable Long id) {
        ProgrammerEntity programmer = programmerService.getProgrammerById(id);
        return ResponseEntity.ok(Objects.requireNonNullElse(programmer, HttpEntity.EMPTY));
    }

    @GetMapping(value = "/" + Consts.TABLE_PROJECTS)
    public ResponseEntity<?> getProjects() {
        List<ProjectEntity> projects = projectService.getAllProjects();
        return ResponseEntity.ok(Objects.requireNonNullElse(projects, HttpEntity.EMPTY));
    }
    @GetMapping(value = "/" + Consts.TABLE_PROJECTS + "/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        Optional<ProjectEntity> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            List<CommentEntity> comments = project.get().getComments();
            comments.sort(Comparator.comparing(CommentEntity::getCreatedAt).reversed());
            project.get().setComments(comments);
            return ResponseEntity.ok(project.get());
        }
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
