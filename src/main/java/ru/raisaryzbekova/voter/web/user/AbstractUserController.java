package ru.raisaryzbekova.voter.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.service.UserService;
import ru.raisaryzbekova.voter.to.UserTo;

import java.util.List;

import static ru.raisaryzbekova.voter.util.ValidationUtil.assureIdConsistent;
import static ru.raisaryzbekova.voter.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    public User get(int id) {
        log.info("get {}", id);
        return userService.get(id);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public User getWithVotes(int id) {
        log.info("getWithVotes {}", id);
        return userService.getWithVotes(id);
    }

    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        userService.update(userTo);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        userService.delete(id);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        userService.enable(id, enabled);
    }
}