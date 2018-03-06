package ru.jswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.jswap.dao.intefaces.FilesDAO;
import ru.jswap.dao.intefaces.PostsDAO;
import ru.jswap.dao.intefaces.UserDAO;
import ru.jswap.entities.FileData;
import ru.jswap.entities.Post;
import ru.jswap.entities.User;
import ru.jswap.service.FileService;
import ru.jswap.validators.FileValidator;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView viewFiles(@SessionAttribute("user") User user, HttpServletRequest request){
        StringBuilder str = new StringBuilder();
        ModelAndView modelAndView = new ModelAndView("viewfiles");
        List<Post> posts = postsDAO.getPosts(user);
        List<FileData> files;


        //<a href="<c:url value='${flowExecutionUrl}/${user.username}/4/[NNM-Club.ru]_Titanik[Rasshirennaya_versiya]-Titanic[ExtendedCut](1997).torrent' />">Download This File (located inside project)</a>
        for (Post p: posts) {
            str.append("<div style=\" border: 3px solid\">\n");
            files = filesDAO.getFiles(p);
            for (FileData fileData: files) {
                //TODO Убрать костыль!!!
                str.append("<a href=\"" + "http://localhost:8080" + "/" + user.getUsername() + "/" + fileData.getPost().getPostPk() + "/" + fileData.getFilename() +"\">" + fileData.getFilename() + "</a> <br />");
            }
            str.append("</div>");
        }

        modelAndView.addObject("viewString", str);

        return modelAndView;
    }
}
