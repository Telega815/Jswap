package ru.jswap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.jswap.dao.intefaces.FilesDAO;
import ru.jswap.dao.intefaces.PostsDAO;
import ru.jswap.dao.intefaces.UserDAO;
import ru.jswap.entities.FileData;
import ru.jswap.entities.Post;
import ru.jswap.entities.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@Component(value = "fileService")
public class FileService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PostsDAO postsDAO;

    @Autowired
    private FilesDAO filesDAO;


    public void writeMultiparFiles(MultipartFile[] multipartFiles, User user) throws IOException{
        user.setPostquantity(user.getPostquantity()+1);

        Long timeMillis = System.currentTimeMillis();
        Post post = new Post(user, new Date(timeMillis), new Time(timeMillis));
        String dirName = user.getPostquantity() +"_"+ post.getDate().toString() +"_"+ post.getTime().toString().replace(':', '-');
        File dir = new File("C:/Projects/jswap/tmpFiles" + File.separator + user.getUsername() + File.separator + dirName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        postsDAO.savePost(post);
        for (MultipartFile multipartFile: multipartFiles) {
            writeMultipartFile(multipartFile, dir, post);
        }
        userDAO.updateUser(user);
    }
    private void writeMultipartFile(MultipartFile multipartFile, File dir, Post post) throws IOException{
        String filename = multipartFile.getOriginalFilename();
        byte[] bytes = multipartFile.getBytes();
        FileData fileData = new FileData(filename, post);

        File uploadedFileDir = new File(dir.getAbsolutePath() + File.separator + filename);

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadedFileDir));
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        filesDAO.saveFile(fileData);
    }
}
