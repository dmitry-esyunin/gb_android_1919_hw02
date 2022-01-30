package com.gb1919.hw02;

public class Calculator {

    private float answer;
    private String action;

    public Calculator(float answer, String action) {
        this.answer =answer;
        this.action = action;
    }

    public String get_answer(){
        return  (this.answer % 10f == 0f) ? Float.toString(this.answer) : Integer.toString((int)this.answer);
    }

    public void action_plus(String value){
        this.answer += Float.parseFloat(value);
    }

    public void action_minus(String value){
        this.answer -= Float.parseFloat(value);
    }


    public void action_multy(String value){
        this.answer *= Float.parseFloat(value);
    }


    public void action_devide(String value){
        this.answer /= Float.parseFloat(value);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
