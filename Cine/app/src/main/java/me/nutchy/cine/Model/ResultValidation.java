package me.nutchy.cine.Model;

/**
 * Created by nutchy on 1/12/2017 AD.
 */

public class ResultValidation {
    private boolean result;
    private String message;

    public ResultValidation(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

}
