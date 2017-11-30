package me.nutchy.cine;

import org.junit.Before;
import org.junit.Test;

import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.ResultValidation;

import static org.junit.Assert.assertFalse;


public class CommentValidationTest {
    private CommentValidation commentValidation;
    private Comment comment;

    @Before
    public void setup(){
        commentValidation = new CommentValidation();
        comment = new Comment();
    }

    @Test
    public void isEmpty(){
        comment.setComment("");
        ResultValidation result = commentValidation.validate(comment.getComment());
        assertFalse(result.getMessage(), result.getResult());
    }

    @Test
    public void isNull(){
        comment.setComment(null);
        ResultValidation result = commentValidation.validate(comment.getComment());
        assertFalse(result.getMessage(), result.getResult());
    }

    @Test
    public void lengthNotMoreThanOneChar(){
        comment.setComment("a");
        ResultValidation result = commentValidation.validate(comment.getComment());
        assertFalse(result.getMessage(), result.getResult());
    }

    @Test
    public void lengthVeryLong(){
        comment.setComment("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
        ResultValidation result = commentValidation.validate(comment.getComment());
        assertFalse(result.getMessage(), result.getResult());
    }
}
