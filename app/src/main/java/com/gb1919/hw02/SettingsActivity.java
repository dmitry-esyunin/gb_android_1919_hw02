package com.gb1919.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private int index_theme = 0;
    private int r_theme_code = 0;
    int[] radio_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            index_theme = extras.getInt(MainActivity.KEY_INTENT);
            r_theme_code = extras.getInt(MainActivity.KEY_INTENT_THEME);
            Log.i("Second Activity accept data:", "index_theme = " + index_theme + ", r_theme_code = " + r_theme_code);
            setTheme(r_theme_code);
        }

        setContentView(R.layout.activity_settings);

        radio_buttons = new int[]{
                R.id.rb_default,
                R.id.rb_red,
                R.id.rb_green,
                R.id.rb_blue
        };

        for (int rb : radio_buttons) findViewById(rb).setOnClickListener(this);

        findViewById(R.id.bt_apply).setOnClickListener(this);


        if (index_theme > radio_buttons.length) index_theme = 0;
        ((RadioButton) findViewById(radio_buttons[index_theme])).setChecked(true); // текущая тема




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case (R.id.rb_default):
                index_theme = 0;
                break;
            case (R.id.rb_red):
                index_theme = 1;
                break;
            case (R.id.rb_green):
                index_theme = 2;
                break;
            case (R.id.rb_blue):
                index_theme = 3;
                break;
            case (R.id.bt_apply):
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY_INTENT, index_theme);
                SettingsActivity.this.setResult(RESULT_OK, intent);
                finish();
                break;

        }

    }


}