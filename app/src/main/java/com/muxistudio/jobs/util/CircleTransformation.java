package com.muxistudio.jobs.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.squareup.picasso.Transformation;

/**
 * Created by ybao on 16/11/16.
 */

public class CircleTransformation implements Transformation {

  @Override public Bitmap transform(Bitmap source) {
    int size = Math.min(source.getWidth(),source.getHeight());

    int x = (source.getWidth() - size) / 2;
    int y = (source.getHeight() - size) /2;
    Bitmap squareBitmap = Bitmap.createBitmap(source,x,y,size,size);
    if (squareBitmap != source){
      source.recycle();
    }
    Bitmap bitmap = Bitmap.createBitmap(size,size,source.getConfig() != null
        ? source.getConfig() : Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint p = new Paint();
    BitmapShader shader = new BitmapShader(squareBitmap,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP);
    p.setShader(shader);
    p.setAntiAlias(true);
    float r = size / 2f;
    canvas.drawCircle(r,r,r,p);
    squareBitmap.recycle();
    return bitmap;
  }

  @Override public String key() {
    return "circle";
  }
}