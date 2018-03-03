package ru.jswap.entities;

import java.util.Objects;

public class FileData {
    private int filePk;
    private String filename;
    private Post post;
//    private int postid;

    public FileData() {
    }

    public FileData(String filename, Post post) {
        this.filename = filename;
        this.post = post;
    }

    //    public FileData(String filename, int postid) {
//        this.filename = filename;
//        this.postid = postid;
//    }

    public int getFilePk() {
        return filePk;
    }

    public void setFilePk(int filePk) {
        this.filePk = filePk;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    //    public int getPostid() {
//        return postid;
//    }
//
//    public void setPostid(int postid) {
//        this.postid = postid;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData fileData = (FileData) o;
        return filePk == fileData.filePk &&
//                postid == fileData.postid &&
                Objects.equals(post,fileData.post) &&
                Objects.equals(filename, fileData.filename);
    }

    @Override
    public int hashCode() {

//        return Objects.hash(filePk, filename, postid);
        return Objects.hash(filePk,filename,post);
    }
}
