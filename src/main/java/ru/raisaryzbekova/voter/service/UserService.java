package ru.raisaryzbekova.voter.service;

import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.to.UserTo;
import ru.raisaryzbekova.voter.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    User getWithVotes(int id);

    List<User> getAll();

    void update(User user);

    void update(UserTo userTo);

    void delete(int id) throws NotFoundException;

    void enable(int id, boolean enable);
}