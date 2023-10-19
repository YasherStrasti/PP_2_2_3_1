package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(long id,User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User showUser(long id);
}
