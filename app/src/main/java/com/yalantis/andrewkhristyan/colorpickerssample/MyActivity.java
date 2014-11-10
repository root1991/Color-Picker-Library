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
                .backgroundColor(getResources().getDrawable(android.R.color.holo_blue_light))
                .animation(true)
                .itemType(ColorPicker.ItemType.SHAPE)
                .build();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.showPicker();
            }
        });
    }


    private List<Integer> getMainColors() {
        List<Integer> mainColors = new ArrayList<Integer>();
        mainColors.add(Color.CYAN);
        mainColors.add(Color.BLUE);
        mainColors.add(Color.RED);
        mainColors.add(Color.YELLOW);
        mainColors.add(Color.CYAN);
        mainColors.add(Color.BLUE);
        mainColors.add(Color.RED);
        mainColors.add(Color.YELLOW);
        mainColors.add(Color.CYAN);
        return mainColors;
    }

    @Override
    public void onColorSelected(int color) {
        textView.setTextColor(color);
    }
}
