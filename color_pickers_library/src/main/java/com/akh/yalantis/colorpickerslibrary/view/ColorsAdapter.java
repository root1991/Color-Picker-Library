package com.akh.yalantis.colorpickerslibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.akh.yalantis.colorpickerslibrary.R;

import java.util.List;

/**
 * Created by andrewkhristyan on 11/5/14.
 */
public class ColorsAdapter extends ArrayAdapter<Integer> {
    private boolean animateItems;
    private ColorPicker.ItemType itemType;

    public ColorsAdapter(Context context, int resource, List<Integer> objects, boolean animateItems, ColorPicker.ItemType itemType) {
        super(context, resource, objects);
        this.animateItems = animateItems;
        this.itemType = itemType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_color, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_color);

            if (animateItems) {
                Animation animationFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.color_anim_scale);
                convertView.startAnimation(animationFadeIn);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageBitmap(drawFiguredBitmap(getItem(position)));

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }

    private Bitmap drawFiguredBitmap(int color) {
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Canvas canvas = new Canvas(bitmap);
        paint.setColor(Color.BLACK);

        if (itemType == ColorPicker.ItemType.CIRCLE) {
            canvas.drawCircle(50, 50, 50, paint);
            paint.setColor(color);
            canvas.drawCircle(50, 50, 47, paint);
        } else {
            canvas.drawRect(new Rect(0, 0, 100, 100), paint);
            paint.setColor(color);
            canvas.drawRect(new Rect(2, 2, 98, 98), paint);
        }
        return bitmap;
    }


}
