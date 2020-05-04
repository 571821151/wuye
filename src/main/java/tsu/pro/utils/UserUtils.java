package tsu.pro.utils;

import tsu.pro.bean.User;
import tsu.pro.controller.UserController;

import javax.servlet.http.HttpSession;

public  class UserUtils {

    public static User GetUser(HttpSession session) {

        Object name = session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = UserController.users.get(name);
        return user;
    }
}
