package com.akh.yalantis.colorpickerslibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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


    public ColorsAdapter(Context context, int resource, List<Integer> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_color, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_color);

            //Animation animationFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.color_anim_scale);
            //convertView.startAnimation(animationFadeIn);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageBitmap(drawCircleBitmap(getItem(position)));
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }

    private Bitmap drawCircleBitmap(int color) {
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        myOptions.inPurgeable = true;

        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Canvas canvas = new Canvas(bitmap);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(50, 50, 50, paint);

        paint.setColor(color);
        canvas.drawCircle(50, 50, 47, paint);
        return bitmap;
    }


}
