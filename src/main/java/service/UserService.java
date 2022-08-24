package service;


import model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(long id);

    User deleteUser(long id);

    User updateUser(long id, User user);
}
