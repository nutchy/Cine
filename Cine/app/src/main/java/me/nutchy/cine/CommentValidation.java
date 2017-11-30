package me.nutchy.cine;

import me.nutchy.cine.Exception.CommentException;
import me.nutchy.cine.Interface.Validation;
import me.nutchy.cine.Model.ResultValidation;

/**
 * Created by nutchy on 1/12/2017 AD.
 */

public class CommentValidation implements Validation{
    @Override
    public ResultValidation validate(String comment) {
        try{
            isEmpty(comment);

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
}
