package web.dao;


import web.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoles();
    Role getRoleByID(Long id);
}
