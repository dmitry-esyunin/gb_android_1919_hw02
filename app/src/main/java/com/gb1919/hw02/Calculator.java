package com.gb1919.hw02;


import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Calculator implements Parcelable {

    final String ACTION_PLUS = "plus";
    final String ACTION_MINUS = "minus";
    final String ACTION_MULTI = "multy";
    final String ACTION_DIVISION = "devide";

    private float a;
    private float b;
    private String action;

    public Calculator(float a, float b, String action) {
        this.a = a;
        this.b = b;
        this.action = action;
    }

    public Calculator() {
        this.a = 0f;
        this.b = 0f;
        this.action = ACTION_PLUS;
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
            case ACTION_PLUS:
                this.a += this.b;
                break;
            case ACTION_MINUS:
                this.a -= this.b;
                break;
            case ACTION_MULTI:
                this.a *= this.b;
                break;
            case ACTION_DIVISION:
                this.a /= this.b;
                break;

        }
        return ((long) this.a == this.a) ? String.format("%d", (long) this.a) : String.format("%s", this.a);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
