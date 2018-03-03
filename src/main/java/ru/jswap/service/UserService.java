package ru.jswap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jswap.dao.intefaces.UserDAO;
import ru.jswap.entities.User;

import java.io.File;

@Component
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public String checkUser(String username) {
        try {
            userDAO.getUser(username);
            return "success";
        } catch (Exception e) {
//            e.printStackTrace();
            return "failed";
        }
    }

    public void createUser(User user) {
        userDAO.saveUser(new User(user.getUsername()));
        String rootPath = "C:/Projects/jswap/tmpFiles";
        File dir = new File(rootPath + File.separator + user.getUsername());
        if(!dir.exists()){
            dir.mkdirs();
        }
    }
}
