package me.nutchy.cine;

import me.nutchy.cine.Exception.CommentException;
import me.nutchy.cine.Interface.Validation;
import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.ResultValidation;



public class CommentValidation implements Validation{
    @Override
    public ResultValidation validate(String comment) {
        try{
            isEmpty(comment);
            isNull(comment);
            lengthNotMoreThanOneChar(comment);
            lengthMoreThanOneHundredFourtyChar(comment);

        }
        catch (Exception e){
            return new ResultValidation(false, e.getMessage());
        }
        return new ResultValidation(true, "Comment is correct pattern");
    }

    public void isEmpty(String cm) throws CommentException {
        if(cm.isEmpty()){
            throw new CommentException("Comment is Empty");
        }
    }

    public void isNull(String cm) throws CommentException {
        if(cm==null){
            throw new CommentException("Comment is null");
        }
    }

    public void lengthNotMoreThanOneChar(String cm) throws CommentException {
        if (cm.length() == 1) {
            throw  new CommentException("Comment is too short.");
        }
    }

    public void lengthMoreThanOneHundredFourtyChar(String cm) throws CommentException{
        if (cm.length() > 140){
            throw new CommentException("Comment is too long.");
        }
    }


}
