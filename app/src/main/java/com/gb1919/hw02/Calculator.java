package com.gb1919.hw02;

import android.util.Log;

public class Calculator {

    private float a;
    private float b;
    private String action;

    public Calculator(float a, float b, String action) {
        this.a = a;
        this.b = b;
        this.action = action;
    }


    public void setA(String a) {
        if (a.equals("")) a = "0";
        this.a = Float.parseFloat(a);
    }

    public void setB(String b) {
        if (b.equals("")) b = "0";
        this.b = Float.parseFloat(b);
    }

    public String getA() {
        return "" + this.a;
    }

    public String getB() {
        return "" + this.b;
    }


    public void setAction(String action) {
        this.action = action;
    }

    public String resolve() {

        switch (this.action) {
            case "plus":
                this.a += this.b;
                break;
            case "minus":
                this.a -= this.b;
                break;
            case "multy":
                this.a *= this.b;
                break;
            case "devide":
                this.a /= this.b;
                break;
        }
        return ((long) this.a == this.a) ? String.format("%d", (long) this.a) : String.format("%s", this.a);
    }
}
