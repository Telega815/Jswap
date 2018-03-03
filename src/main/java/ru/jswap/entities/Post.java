package ru.jswap.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Post {
    private int postPk;
    private String commentary;
//    private int userid;
    private User user;
    private Date date;
    private Time time;

    public Post() {
    }

//    public Post(int userid, Date date, Time time) {
//        this.userid = userid;
//        this.date = date;
//        this.time = time;
//    }



//    public Post(String commentary, int userid, Date date, Time time) {
//        this.commentary = commentary;
//        this.userid = userid;
//        this.date = date;
//        this.time = time;
//    }


    public Post(User user, Date date, Time time) {
        this.user = user;
        this.date = date;
        this.time = time;
    }

    public Post(String commentary, User user, Date date, Time time) {
        this.commentary = commentary;
        this.user = user;
        this.date = date;
        this.time = time;
    }

    public int getPostPk() {
        return postPk;
    }

    public void setPostPk(int postPk) {
        this.postPk = postPk;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

//    public int getUserid() {
//        return userid;
//    }
//
//    public void setUserid(int userid) {
//        this.userid = userid;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postPk == post.postPk &&
//                userid == post.userid &&
                Objects.equals(user, post.user) &&
                Objects.equals(commentary, post.commentary) &&
                Objects.equals(date, post.date) &&
                Objects.equals(time, post.time);
    }

    @Override
    public int hashCode() {

//        return Objects.hash(postPk, commentary, userid, date, time);
        return Objects.hash(postPk, commentary, user, date, time);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
