package spring.finalProject.services;

import spring.finalProject.entities.models.Role;

import java.util.List;

public interface RoleService {
    void addFromController(String name);
    void updateFromController(Long id, String name);
    void deleteFromController(Long id);

    Role getOneByName(String name);
    Role getRoleById(long id);
    Role add(Role role);
    Role save(Role role);
    void delete(Role role);
    List<Role> getAllRoles();
}
