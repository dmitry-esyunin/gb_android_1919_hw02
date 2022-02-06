package com.gb1919.hw02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQESTCODE = 3432341;
    static String KEY_INTENT = "key_theme_index";

    final String ACTION_PLUS = "plus";
    final String ACTION_MINUS = "minus";
    final String ACTION_MULTI = "multy";
    final String ACTION_DIVISION = "devide";
    final String ACTION_POINT = ".";
    final char char_ACTION_POINT = '.';
    final String BUTTON_PREFIX = "button_";
    final int BUTTON_PREFIX_LEN = BUTTON_PREFIX.length();
    private static final String PREF_NAME = "key_pref";
    private static final String PREF_THEME_KEY = "key_pref_theme";
    Random random = new Random();

    private TextView tv_result;
    private Calculator calculator = new Calculator();
    boolean is_number_completed;
    int[] themes;

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
        themes();
//        setTheme(getAppTheme());
        setTheme(getAppThemeByIndex());

        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.text_result);
        is_number_completed = true;

        add_listeners();
    }


    public void themes() {
        themes = new int[]{
                R.style.Theme_Hw02,
                R.style.myThemeRed,
                R.style.myThemeGreen,
                R.style.myThemeBlue

        };
    }

    private void add_listeners() {

//        tv_result.setOnClickListener(this);

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
                R.id.button_point,
                R.id.imageView

        };
        for (int button : buttons) {
            findViewById(button).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {

        String button_name = getResources().getResourceEntryName(view.getId()).substring(BUTTON_PREFIX_LEN);


        switch (view.getId()) {


            case (R.id.imageView):   //  случайный выбор темы
            case (R.id.text_result):
                /*int theme_index = (int) random.nextInt(themes.length);
                setAppTheme(theme_index);
                recreate();*/
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.putExtra(KEY_INTENT, getThemeIndex());
                startActivityForResult(intent, REQESTCODE);


                break;
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


    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(PREF_THEME_KEY, codeStyle);
        editor.apply();
    }



    private int getAppThemeByIndex() {
        int i = getThemeIndex();
        if (i > themes.length) i = 1;
        Log.i("getAppThemeByIndex", "индекс = " + i + "  themes[index] = " + themes[i]);
        return themes[i];
    }

    private int getThemeIndex() {
        SharedPreferences sharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        return sharedPref.getInt(PREF_THEME_KEY, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQESTCODE || resultCode != RESULT_OK) return;

        int index = data.getIntExtra(KEY_INTENT, 0);
        Log.i("", "получил индекс = " + index + "  themes[index] = " + themes[index]);


        setAppTheme(index);
        recreate();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getAppThemeByIndex();
        Log.i("onDestroy", "**********" );
    }
}