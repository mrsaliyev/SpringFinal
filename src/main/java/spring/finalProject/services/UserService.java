package spring.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import spring.finalProject.entities.models.Role;
import spring.finalProject.entities.models.Users;
import spring.finalProject.repositories.RoleRepository;
import spring.finalProject.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public Users findUserById(Long userId) {
        Optional<Users> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new Users());
    }

    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(Users user) {
        Users userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        Optional<Role> role = roleRepository.findById(1L);

        if (role.isPresent()) {
            user.setRoles(Collections.singleton(role.get()));
        } else {
            Role r = new Role();
            r.setRoleName("USER");
            r.setId(1L);
            roleRepository.save(r);
            user.setRoles(Collections.singleton(r));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}