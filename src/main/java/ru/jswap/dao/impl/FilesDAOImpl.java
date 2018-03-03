package ru.jswap.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jswap.dao.intefaces.FilesDAO;
import ru.jswap.entities.FileData;
import ru.jswap.entities.Post;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Component
public class FilesDAOImpl implements FilesDAO {

    @Autowired
    SessionFactory sessionFactory;

    private List<FileData> files;

    @Transactional
    @Override
    public List<FileData> getFiles() {
        files = sessionFactory.getCurrentSession()
                .createCriteria(FileData.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return files;
    }

    @Transactional
    @Override
    public FileData getFile(String filename) {
        return (FileData) sessionFactory.getCurrentSession().createQuery("from FileData where filename = :filename").setString("filename", filename).list().get(0);
    }

    @Transactional
    @Override
    public List<FileData> getFiles(Post post) {
        files = sessionFactory.getCurrentSession()
                .createQuery("from FileData where postid = :postid").setInteger("postid", post.getPostPk()).list();
        return files;
    }

    @Override
    public List<FileData> getFiles(Post[] posts) {
        List<FileData> allFiles = new LinkedList<>();
        for (Post post:posts) {
            allFiles.addAll(getFiles(post));
        }
        return allFiles;
    }

    @Transactional
    @Override
    public void saveFile(FileData fileData) {
        sessionFactory.getCurrentSession().save(fileData);
    }

    @Transactional
    @Override
    public void deleteFile(FileData fileData) {
        sessionFactory.getCurrentSession().delete(fileData);
    }
}