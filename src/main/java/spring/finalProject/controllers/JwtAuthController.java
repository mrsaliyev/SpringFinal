package spring.finalProject.controllers;

import spring.finalProject.config.jwt.JwtRequestNewPost;
import spring.finalProject.entities.models.ProjectEntity;
import spring.finalProject.entities.models.Role;
import spring.finalProject.entities.models.Users;
import spring.finalProject.config.jwt.JwtRequest;
import spring.finalProject.config.jwt.JwtResponse;
import spring.finalProject.config.jwt.JwtTokenGenerator;
import spring.finalProject.services.ProjectService;
import spring.finalProject.services.RoleService;
import spring.finalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthController {
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenGenerator.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }
    }

    @PostMapping(value = "/addBook")
    @PreAuthorize("isAnonymous()")
    public void addNews(@RequestBody JwtRequestNewPost newBook) {
        ProjectEntity project = new ProjectEntity();
        project.setBody(newBook.getBody());
        project.setImgPath(newBook.getImgPath());
        project.setShortDescription(newBook.getShortDesc());
        project.setFooter(newBook.getFooter());
        project.setTitle(newBook.getFooter());
        project.setCreatedAt(java.sql.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        projectService.save(project);
    }


    @PostMapping(value = "/auth/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> signUp(@RequestBody JwtRequest newUser) {
        Users user = userService.getUserByUsername(newUser.getUsername());
        if (user == null) {
            List<Role> roles = new ArrayList<>(1);
            roles.add(roleService.getOneByName("USER"));

            user = new Users();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setRoles(roles);
            userService.saveUser(user);
        }
        return ResponseEntity.ok(newUser);
    }

    @GetMapping(value = "/auth/profile/{token}")
    public ResponseEntity<?> getProfile(@PathVariable String token) {
        String email = jwtTokenGenerator.getEmailFromToken(token);
        Users user = userService.getUserByUsername(email);
        return ResponseEntity.ok(Objects.requireNonNullElse(user, HttpEntity.EMPTY));
    }
}
