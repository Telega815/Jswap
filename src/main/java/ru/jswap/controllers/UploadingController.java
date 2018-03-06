package ru.jswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

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
    public ModelAndView upload(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result, @SessionAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            fileValidator.validate(uploadedFile, result);

            if (result.hasErrors()){
                modelAndView.setViewName("page");
            }else {
                MultipartFile[] multipartFiles = uploadedFile.getFiles();
                fileService.writeMultiparFiles(multipartFiles, user);

                RedirectView redirectView = new RedirectView("/" + user.getUsername());
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

    @RequestMapping(value = "/{username}/{postid}/{filename:.+}")
    public void download(@SessionAttribute("user") User user, @PathVariable("filename") String filename, @PathVariable("username") String username, @PathVariable("postid") int postid, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        FileData fileData = fileService.getFile(filename, postid);
        String filePath = fileService.getFilePath(fileData);

        File file = new File(filePath);

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + filename +"\""));
        response.setContentLength((int)file.length());
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            //TODO catch block
            e.printStackTrace();
        }

//        RedirectView redirectView = new RedirectView("/" + username + "/viewFiles");
//        redirectView.setStatusCode(HttpStatus.FOUND);
//        modelAndView.setView(redirectView);
//        modelAndView.addObject("user", user);
//
//        return modelAndView;
    }
}
