package ru.jswap.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    private int id;
    private String username;
    private Integer postquantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPostquantity() {
        return postquantity;
    }

    public void setPostquantity(Integer postquantity) {
        this.postquantity = postquantity;
    }


    public User() {
    }

    public User(String username) {
        this.postquantity = 0;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(postquantity, user.postquantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, postquantity);
    }


}
