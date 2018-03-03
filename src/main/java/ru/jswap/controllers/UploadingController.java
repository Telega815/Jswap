package ru.jswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.jswap.dao.intefaces.FilesDAO;
import ru.jswap.dao.intefaces.PostsDAO;
import ru.jswap.dao.intefaces.UserDAO;
import ru.jswap.entities.User;
import ru.jswap.objects.UploadedFile;
import ru.jswap.service.FileService;
import ru.jswap.validators.FileValidator;

import java.io.IOException;

@Controller
public class UploadingController {

    @Autowired
    FileService fileService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    FileValidator fileValidator;

    @PostMapping(value = "/{username}/upload")
    @ResponseBody
    public ModelAndView upload(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result, @PathVariable("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        try {

            User user = userDAO.getUser(username);
            fileValidator.validate(uploadedFile, result);

            if (result.hasErrors()){
                modelAndView.setViewName("page");
            }else {
                MultipartFile[] multipartFiles = uploadedFile.getFiles();
                fileService.writeMultiparFiles(multipartFiles, user);

                RedirectView redirectView = new RedirectView("/" + username);
                redirectView.setStatusCode(HttpStatus.FOUND);
                modelAndView.setView(redirectView);
                modelAndView.addObject("user", user);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return modelAndView;
    }
}
