package ru.jswap.dao.intefaces;

import ru.jswap.entities.Post;
import ru.jswap.entities.User;

import java.util.List;

public interface PostsDAO {

    List<Post> getPosts();
    List<Post> getPosts(User user);
    int savePost(Post post);
    void deletePost(Post post);
}
