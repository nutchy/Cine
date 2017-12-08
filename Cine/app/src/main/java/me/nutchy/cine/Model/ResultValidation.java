package me.nutchy.cine.Model;

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
