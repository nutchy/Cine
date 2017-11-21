package me.nutchy.cine.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String imgurl;
    private String email;
    private String name;
    private String uid;

    public User() {
    }

    public User(String imgurl, String email, String name) {
        this.imgurl = imgurl;
        this.email = email;
        this.name = name;
    }

    public User(String imgurl, String email, String name, String uid) {
        this.imgurl = imgurl;
        this.email = email;
        this.name = name;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
