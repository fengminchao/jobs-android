package com.muxistudio.jobs.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import com.muxistudio.jobs.Constant;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by ybao on 16/12/2.
 */

public class PictureUtil {

  public static Bitmap transToRound(Bitmap source){
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

  public static Bitmap drawableToBitmap(Drawable drawable) {
    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
            : Bitmap.Config.RGB_565);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    drawable.draw(canvas);
    return bitmap;
  }

  /**
   * 保存用户头像到本地
   * @param bitmap
   * @param name 用户头像文件名,包括后缀
   */
  public static void saveAvatorToDisk(Bitmap bitmap,String name){
    File file = new File(Constant.STORGE_PATH + "/" + name);
    if (file.exists()){
      file.delete();
    }
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(file);
      bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      try {
        if (fos != null){
          fos.close();
        }
      }catch (Exception e){
        e.printStackTrace();
      }
    }
  }
}
