package com.abdulaziz.customviewexample;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by abdulaziz on 13/08/16.
 */
public class AspectImageView extends ImageView {
    public AspectImageView(Context context) {
        super(context);
    }

    public AspectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AspectImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int desiredLenght = 0;
        float aspectRatio = 1f;
        Drawable imageDrawable = getDrawable();
        if (imageDrawable != null){
            desiredLenght = imageDrawable.getIntrinsicHeight();
            aspectRatio = (float)imageDrawable.getIntrinsicHeight()/
                    (float)imageDrawable.getIntrinsicWidth();
        }
        int height = View.resolveSize(desiredLenght,heightMeasureSpec);

        int width = (int)(height*aspectRatio);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if(mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY){
            if(width > size){
                width = size;
                height = (int)(width/aspectRatio);
            }
        }

        setMeasuredDimension(width,height);

    }
}
