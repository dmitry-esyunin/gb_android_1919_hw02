package com.gb1919.hw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private int[] buttons;
    Calculator calculator;
    boolean is_answered = true;
    String last_value = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.text_result);

        calculator = new Calculator(0f, 0f, "plus");
        is_answered = true;

        buttons = new int[]{
                R.id.button_0,
                R.id.button_1,
                R.id.button_2,
                R.id.button_3,
                R.id.button_4,
                R.id.button_5,
                R.id.button_6,
                R.id.button_7,
                R.id.button_8,
                R.id.button_9,
                R.id.button_clear,
                R.id.button_plus,
                R.id.button_minus,
                R.id.button_multy,
                R.id.button_devide,
                R.id.button_enter,
                R.id.button_point
        };
        for (int button : buttons) {
            findViewById(button).setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View view) {

        String button_name = getResources().getResourceEntryName(view.getId()).substring(7);

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
                if (is_answered) {
                    tv_result.setText(button_name);
                    is_answered = false;
                } else {
                    tv_result.setText(tv_result.getText().toString() + button_name);
                }
                break;
            }
            case (R.id.button_clear): {
                is_answered = true;
                calculator.setAction("plus");
                calculator.setA("0");
                calculator.setB("0");
                tv_result.setText("");
                break;
            }
            case (R.id.button_point): {

                boolean found = false;
                for (int i = 0; i < tv_result.getText().toString().length(); i++) {
                    if (tv_result.getText().toString().charAt(i) == '.') {
                        found = true;
                        break;
                    }
                }
                if (found) break;
                tv_result.setText(tv_result.getText().toString() + ".");
                is_answered =false;
                break;

            }


            case (R.id.button_plus):
            case (R.id.button_minus):
            case (R.id.button_multy):
            case (R.id.button_devide): {

                if (!is_answered) {
                   calculator.setB(tv_result.getText().toString());
                   tv_result.setText(calculator.resolve());
                   is_answered = true;
                }

                calculator.setAction(button_name);


                break;
            }
            case (R.id.button_enter): {
                if (!is_answered) {
                    calculator.setB(tv_result.getText().toString());
                    is_answered = true;
                }
                tv_result.setText(calculator.resolve());
            }
            default:
                break;
        }
    }
}