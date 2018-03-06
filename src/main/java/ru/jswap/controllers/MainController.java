package ru.jswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.jswap.dao.intefaces.FilesDAO;
import ru.jswap.dao.intefaces.PostsDAO;
import ru.jswap.dao.intefaces.UserDAO;
import ru.jswap.entities.FileData;
import ru.jswap.entities.Post;
import ru.jswap.entities.User;
import ru.jswap.objects.UploadedFile;
import ru.jswap.service.FileService;
import ru.jswap.validators.FileValidator;

import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes(value = "user")
public class MainController {

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

    @GetMapping(value = "/")
    public String main() {
        return "redirect:service/login";
    }

    @RequestMapping(value = "/{username}")
    public ModelAndView red(@PathVariable("username") String username) {
        try {
            User user = userDAO.getUser(username);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("page");
            modelAndView.addObject("user", user);
            return modelAndView;
        } catch (Exception e) {
//            e.printStackTrace();
            return new ModelAndView("page404error");
        }
    }
}

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public class ResourceNotFoundException extends RuntimeException{}
//
//    @RequestMapping(value = "/*", method = RequestMethod.GET)
//    public ModelAndView bla(HttpServletRequest request){
//        StringBuffer str = request.getRequestURL();
//        System.out.println(str);
//        int i = str.indexOf("login");
//        String s2 = str.substring(i);
//        if(s2.equals("login"))
//            return redir();
//        throw new ResourceNotFoundException();
//    }

