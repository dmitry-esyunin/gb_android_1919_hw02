package com.gb1919.hw02;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String ACTION_PLUS = "plus";
    final String ACTION_MINUS = "minus";
    final String ACTION_MULTI = "multy";
    final String ACTION_DIVISION = "devide";
    final String ACTION_POINT = ".";
    final char char_ACTION_POINT = '.';
    final String BUTTON_PREFIX = "button_";
    final int BUTTON_PREFIX_LEN = BUTTON_PREFIX.length();


    private TextView tv_result;
    private Calculator calculator = new Calculator();
    boolean is_number_completed;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("calculator", calculator);
        outState.putString("tv_result", (String) tv_result.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable("calculator");
        tv_result.setText(savedInstanceState.getCharSequence("tv_result"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.text_result);
        is_number_completed = true;
        add_listeners();
    }

    private void add_listeners() {
        int[] buttons = new int[]{
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

        String button_name = getResources().getResourceEntryName(view.getId()).substring(BUTTON_PREFIX_LEN);

        switch (view.getId()) {
            case (R.id.button_0):   // numbers
            case (R.id.button_1):
            case (R.id.button_2):
            case (R.id.button_3):
            case (R.id.button_4):
            case (R.id.button_5):
            case (R.id.button_6):
            case (R.id.button_7):
            case (R.id.button_8):
            case (R.id.button_9): {
                if (is_number_completed) {
                    tv_result.setText(button_name);
                    is_number_completed = false;
                } else {
                    tv_result.setText(tv_result.getText().toString() + button_name);
                }
                break;
            }
            case (R.id.button_clear): {
                is_number_completed = true;
                calculator.setAction(ACTION_PLUS);
                calculator.setA("0");
                calculator.setB("0");
                tv_result.setText("");
                break;
            }
            case (R.id.button_point): {
                boolean found = false;
                for (int i = 0; i < tv_result.getText().toString().length(); i++) {
                    if (tv_result.getText().toString().charAt(i) == char_ACTION_POINT) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
                tv_result.setText(tv_result.getText().toString() + ACTION_POINT);
                is_number_completed = false;
                break;
            }
            case (R.id.button_plus):     // operations
            case (R.id.button_minus):
            case (R.id.button_multy):
            case (R.id.button_devide): {
                if (!is_number_completed) {
                    calculator.setB(tv_result.getText().toString());
                    tv_result.setText(calculator.resolve());
                    is_number_completed = true;
                }
                calculator.setAction(button_name);
                break;
            }
            case (R.id.button_enter): {
                if (!is_number_completed) {
                    calculator.setB(tv_result.getText().toString());
                    is_number_completed = true;
                }
                tv_result.setText(calculator.resolve());
                break;
            }
            default:
                break;
        }
    }
}