package me.nutchy.cine.Model;

public class Comment {
    private String comment, fullName, uid, commentId;
    private int movieId;

    public Comment(String comment, String fullName, String uid, int movieId, String commentId) {
        this.comment = comment;
        this.fullName = fullName;
        this.uid = uid;
        this.movieId = movieId;
        this.commentId = commentId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
