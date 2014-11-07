package com.yalantis.andrewkhristyan.colorpickerssample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.akh.yalantis.colorpickerslibrary.view.ColorPicker;
import com.akh.yalantis.colorpickerslibrary.view.ColorSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity implements ColorSelectedListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        textView = (TextView) findViewById(R.id.textView_sample);

        final ColorPicker picker = new ColorPicker.ColorPickerBuilder(this)
                .colors(getMainColors()).setHolderView(textView)
                .colorsListener(this)
                .backgroundColor(getResources().getDrawable(android.R.color.black))
                .build();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.showPicker();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Integer> getMainColors() {
        List<Integer> mainColors = new ArrayList<Integer>();
        mainColors.add(Color.CYAN);
        mainColors.add(Color.BLUE);
        mainColors.add(Color.RED);
        mainColors.add(Color.YELLOW);
        return mainColors;
    }

    @Override
    public void onColorSelected(int color) {
        textView.setTextColor(color);
    }
}
