package spring.finalProject.services.impl;

import spring.finalProject.entities.models.Role;
import spring.finalProject.repositories.RoleRepository;
import spring.finalProject.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addFromController(String name) {
        Role role = new Role();
        role.setRoleName(name);
        this.add(role);
    }

    @Override
    public Role getOneByName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public void updateFromController(Long id, String name) {
        Role role = this.getRoleById(id);
        if (role != null) {
            role.setRoleName(name);
            this.save(role);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        Role role = this.getRoleById(id);
        if (role != null) {
            this.delete(role);
        }
    }

    @Override
    public Role getRoleById(long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
