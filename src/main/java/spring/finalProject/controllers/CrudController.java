package spring.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.finalProject.config.Consts;
import spring.finalProject.config.jwt.JwtTokenGenerator;
import spring.finalProject.controllers.requests.CommentRequest;
import spring.finalProject.controllers.requests.ProgrammerRequest;
import spring.finalProject.controllers.requests.ProjectRequest;
import spring.finalProject.entities.models.*;
import spring.finalProject.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CrudController {
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProgrammerService programmerService;
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/{token}/" + Consts.ADD + "/" + Consts.TABLE_CATEGORIES)
    public ResponseEntity<?> addCategory(@PathVariable String token, @RequestBody String name) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        name = name.replace("+", " ");
        name = name.replace("=", "");

        CategoryEntity category = new CategoryEntity();
        category.setCategory(name);
        return ResponseEntity.ok(categoryService.add(category));
    }

    @DeleteMapping(value = "/{token}/" + Consts.DELETE + "/" + Consts.TABLE_CATEGORIES)
    public ResponseEntity<?> deleteCategory(@PathVariable String token, @RequestBody String id) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        CategoryEntity category = categoryService.getCategoryById(Long.parseLong(id));
        categoryService.delete(category);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping(value = "/{token}/" + Consts.ADD + "/" + Consts.TABLE_PROJECTS)
    public ResponseEntity<?> addProject(@PathVariable String token, @RequestBody ProjectRequest request) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        ProjectEntity project = new ProjectEntity();
        project.setTitle(request.getTitle());
        project.setImgPath(request.getImg());
        project.setShortDescription(request.getShortDescription());
        project.setBody(request.getBody());

        CategoryEntity category = categoryService.getCategoryById(request.getCategoryId());
        List<CategoryEntity> cats = new ArrayList<>();
        cats.add(category);
        project.setCategory(cats);

        return ResponseEntity.ok(projectService.add(project));
    }

    @DeleteMapping(value = "/{token}/" + Consts.DELETE + "/" + Consts.TABLE_PROJECTS)
    public ResponseEntity<?> deleteProject(@PathVariable String token, @RequestBody String id) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        Optional<ProjectEntity> project = projectService.getProjectById(Long.parseLong(id));
        if (project.isPresent()) {
            ProjectEntity proj = project.get();
            projectService.delete(proj);
        }
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping(value = "/{token}/" + Consts.ADD + "/" + Consts.TABLE_PROGRAMMERS)
    public ResponseEntity<?> addProgrammer(@PathVariable String token, @RequestBody ProgrammerRequest request) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        ProgrammerEntity programmer = new ProgrammerEntity();
        programmer.setName(request.getName());
        programmer.setLast_name(request.getLastName());
        programmer.setEmail(request.getEmail());
        programmer.setDescription(request.getDescription());
        programmer.setProfile_img(request.getImg());

        return ResponseEntity.ok(programmerService.add(programmer));
    }

    @DeleteMapping(value = "/{token}/" + Consts.DELETE + "/" + Consts.TABLE_PROGRAMMERS)
    public ResponseEntity<?> deleteProgrammer(@PathVariable String token, @RequestBody String id) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        ProgrammerEntity programmer = programmerService.getProgrammerById(Long.parseLong(id));
        if (programmer != null) {
            programmerService.delete(programmer);
        }
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping(value = "/{token}/" + Consts.ADD + "/" + Consts.TABLE_COMMENTS)
    public ResponseEntity<?> addComment(@PathVariable String token, @RequestBody CommentRequest request) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        CommentEntity comment = new CommentEntity();
        comment.setComment(request.getComment());
        comment.setAuthor(user);

        Optional<ProjectEntity> projectOpt = projectService.getProjectById(request.getProjectId());
        ProjectEntity project;
        if (projectOpt.isPresent()) {
            project = projectOpt.get();
            List<CommentEntity> projectComments = project.getComments();
            projectComments.add(comment);
            project.setComments(projectComments);
            commentService.add(comment);
            projectService.save(project);
        }

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(value = "/{token}/" + Consts.DELETE + "/" + Consts.TABLE_COMMENTS)
    public ResponseEntity<?> deleteComment(@PathVariable String token, @RequestBody String id) {
        String login = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(login);

        CommentEntity comment = commentService.getOneById(Long.parseLong(id));
        if (comment != null && user.getId().equals(comment.getAuthor().getId())) {
            commentService.delete(comment);
        }
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping(value = "/searchProjects/{title}")
    public ResponseEntity<?> getSearchProjectByTitle(@PathVariable(name = "title") String title) {
        if(title != null){
            List<ProjectEntity> projectEntities = projectService.getLatestProjectByTitle(title);
            return new ResponseEntity<>(projectEntities, HttpStatus.OK);
        }
        return new ResponseEntity<>("empty",HttpStatus.OK);
    }
}
