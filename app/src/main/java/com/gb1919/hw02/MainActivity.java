package com.gb1919.hw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private int[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.text_result);

        Calculator calculator = new Calculator(0f, "plus");


        buttons = new int[]{
                R.id.button_0      ,
                R.id.button_1      ,
                R.id.button_2      ,
                R.id.button_3      ,
                R.id.button_4      ,
                R.id.button_5      ,
                R.id.button_6      ,
                R.id.button_7      ,
                R.id.button_8      ,
                R.id.button_9      ,
                R.id.button_clear  ,
                R.id.button_plus   ,
                R.id.button_minus  ,
                R.id.button_multy  ,
                R.id.button_devide ,
                R.id.button_enter  ,
                R.id.button_point
        };
        for (int button: buttons){
            findViewById(button).setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View view) {

        String button_name =  getResources().getResourceEntryName(view.getId()).substring(7);
        CharSequence current_answer = tv_result.getText();


        switch (view.getId()) {
            case (R.id.button_0):
            case (R.id.button_1):
            case (R.id.button_2):
            case (R.id.button_3):
            case (R.id.button_4):
            case (R.id.button_5):
            case (R.id.button_6):
            case (R.id.button_7):
            case (R.id.button_8):
            case (R.id.button_9): {
                tv_result.setText( (current_answer == "")?  button_name: current_answer + button_name);
                break;
            }
            case (R.id.button_clear): {
                tv_result.setText("");
                break;
            }
            case (R.id.button_point): {
                boolean found = false;
                for (int i = 0; i < current_answer.length(); i++) {
                    if (current_answer.charAt(i) == '.') {found =true; break;}
                }
                if (found) break;

                tv_result.setText(current_answer + ".");
                break;
            }



            default:
                break;
        }
    }
}