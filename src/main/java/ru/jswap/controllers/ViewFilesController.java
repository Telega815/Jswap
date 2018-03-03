package ru.jswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.jswap.dao.intefaces.FilesDAO;
import ru.jswap.dao.intefaces.PostsDAO;
import ru.jswap.dao.intefaces.UserDAO;
import ru.jswap.entities.FileData;
import ru.jswap.entities.Post;
import ru.jswap.entities.User;
import ru.jswap.service.FileService;
import ru.jswap.validators.FileValidator;

import java.util.List;

@Controller
public class ViewFilesController {

    @Autowired
    FileService fileService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    PostsDAO postsDAO;

    @Autowired
    FilesDAO filesDAO;

    @Autowired
    FileValidator fileValidator;

    @GetMapping(value = "/{username}/viewFiles")
    public ModelAndView viewFiles(@ModelAttribute("user") User user, @PathVariable("username") String username){
        StringBuilder str = new StringBuilder();
        ModelAndView modelAndView = new ModelAndView("viewfiles");

        List<Post> posts = postsDAO.getPosts(userDAO.getUser(username));
        List<FileData> files;
//        for (Post p: posts) {
//            files.addAll(filesDAO.getFiles(p));
//        }

        for (Post p: posts) {
            str.append("<div>\n");
            files = filesDAO.getFiles(p);
            for (FileData fileData: files) {
                str.append("<p>" + fileData.getFilename() + "</p>\n");
            }
            str.append("</div>");
        }

        modelAndView.addObject("viewString", str);

        return modelAndView;
    }
}
