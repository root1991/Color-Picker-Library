package com.akh.yalantis.colorpickerslibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.akh.yalantis.colorpickerslibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewkhristyan on 11/7/14.
 */
public class ColorPicker extends PopupWindow {

    public enum ItemType {
       CIRCLE, SHAPE;
    }

    private List<Integer> shadeColors;
    private boolean needAnimation = false;
    private int color;
    private ColorsAdapter shadeColorsAdapter;
    private ColorPickerBuilder builder;


    public ColorPicker(ColorPickerBuilder builder) {
        super(builder.context);
        this.builder = builder;
        initPopuView();
    }


    private void initPopuView() {
        View popupView = LayoutInflater.from(builder.context).inflate(R.layout.dialog_color_picker, null);

        setContentView(popupView);
        setFocusable(true);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(builder.backgroundColor);
        setOutsideTouchable(true);

        if (needAnimation) {
            //setAnimationStyle(R.style.Animation);
        }
        GridView gridViewMainColors = (GridView) popupView.findViewById(R.id.gridView_main_colors);
        final GridView gridViewShadeColors = (GridView) popupView.findViewById(R.id.gridView_shade_colors);

        gridViewMainColors.setNumColumns(builder.mainColors.size());
        gridViewShadeColors.setNumColumns(builder.mainColors.size());

        ColorsAdapter colorsAdapter = new ColorsAdapter(builder.context, R.layout.item_color, builder.mainColors);
        shadeColors = getListOfShadeColors(Color.RED);
        shadeColorsAdapter = new ColorsAdapter(builder.context, R.layout.item_color,
                shadeColors);
        gridViewMainColors.setAdapter(colorsAdapter);
        gridViewMainColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                color = builder.mainColors.get(position);
                shadeColors = getListOfShadeColors(color);
                shadeColorsAdapter = new ColorsAdapter(builder.context, R.layout.item_color,
                        shadeColors);
                gridViewShadeColors.setAdapter(shadeColorsAdapter);
            }
        });

        gridViewShadeColors.setAdapter(shadeColorsAdapter);
        gridViewShadeColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (builder.listener == null) {
                    throw new ColorChangeListenerException("Your class has to implement ColorChangedListener");
                }
                builder.listener.onColorSelected(shadeColors.get(position));
            }
        });
    }

    public void showPicker() {
        showAsDropDown(builder.view, builder.x, builder.y);
    }


    private List<Integer> getListOfShadeColors(int color) {
        int shadesQuantity = builder.mainColors.size();
        List<Integer> shadeColors = new ArrayList<Integer>();
        float[] dividers = {1.0f, 1.3f, 1.4f, 1.6f, 2.0f, 2.5f, 3.5f};

        for (int i = 0; i < shadesQuantity; i++) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 1f / dividers[i];
            shadeColors.add(Color.HSVToColor(hsv));
        }
        if (color != Color.WHITE) {
            for (int i = 0; i < shadesQuantity; i++) {
                float[] hsv = new float[3];
                Color.colorToHSV(color, hsv);
                hsv[1] = 1f / dividers[i];
                shadeColors.add(Color.HSVToColor(hsv));
            }
        }
        return shadeColors;
    }

    public static class ColorPickerBuilder {
        private Context context;
        private View view;
        private int x, y;
        private boolean needAnimation;
        private List<Integer> mainColors;
        private ColorSelectedListener listener;
        private Drawable backgroundColor;
        private int defaultColor;
        private ItemType itemType;

        public ColorPickerBuilder(Context context) {
            this.context = context;
        }

        public ColorPickerBuilder setHolderView(View view) {
            this.view = view;
            return this;
        }

        public ColorPickerBuilder setCordinates(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public ColorPickerBuilder animation(boolean needAnimation) {
            this.needAnimation = needAnimation;
            return this;
        }

        public ColorPickerBuilder colors(List<Integer> colors) {
            this.mainColors = colors;
            return this;
        }

        public ColorPickerBuilder colorsListener(ColorSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        public ColorPickerBuilder backgroundColor(Drawable backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public ColorPickerBuilder defaultColor(int defaultColor) {
            this.defaultColor = defaultColor;
            return this;
        }

        public ColorPickerBuilder itemType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public ColorPicker build() {
            return new ColorPicker(ColorPickerBuilder.this);
        }


    }


}
