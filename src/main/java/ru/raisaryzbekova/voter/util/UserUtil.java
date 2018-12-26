package ru.raisaryzbekova.voter.util;

import ru.raisaryzbekova.voter.model.User;
import ru.raisaryzbekova.voter.to.UserTo;

public class UserUtil {

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}