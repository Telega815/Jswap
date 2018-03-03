package ru.jswap.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jswap.dao.intefaces.PostsDAO;
import ru.jswap.entities.Post;
import ru.jswap.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PostsDAOImpl implements PostsDAO {

    @Autowired
    SessionFactory sessionFactory;

    List<Post> posts;

    @Transactional
    @Override
    public List<Post> getPosts() {
        posts = sessionFactory.getCurrentSession()
                .createCriteria(Post.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return posts;
    }

    @Transactional
    @Override
    public List<Post> getPosts(User user) {
        posts = sessionFactory.getCurrentSession().createQuery("from Post where user = :user").setParameter("user", user).list();
        return posts;
    }

    @Transactional
    @Override
    public void savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Transactional
    @Override
    public void deletePost(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }
}
