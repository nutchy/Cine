package me.nutchy.cine.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {
    private String comment, fullName, uid, commentId, avatar;
    private int movieId;

    public Comment(){}

    public Comment(String comment, String fullName, String uid, int movieId, String commentId, String avatar) {
        this.comment = comment;
        this.fullName = fullName;
        this.uid = uid;
        this.commentId = commentId;
        this.avatar = avatar;
        this.movieId = movieId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
