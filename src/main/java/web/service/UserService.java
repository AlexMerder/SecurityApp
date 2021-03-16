package web.service;

import web.model.User;

import java.util.List;


public interface UserService {
    List<User> index();

    void save(User person, Long[] rolesID);

    void update(User updatedPerson,Long[] rolesID); //здесь убрал Long id

    void delete(Long id);

    User getUserByFirstName(String name);

    User getUserById(Long id);

}
