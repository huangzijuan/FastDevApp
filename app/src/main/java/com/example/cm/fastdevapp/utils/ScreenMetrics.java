package com.example.cm.fastdevapp.utils;


/**
 * 方法一：

 WindowManager wm = (WindowManager) this
 .getSystemService(Context.WINDOW_SERVICE);
 int width = wm.getDefaultDisplay().getWidth();
 int height = wm.getDefaultDisplay().getHeight();
 方法二：

 WindowManager wm1 = this.getWindowManager();
 int width1 = wm1.getDefaultDisplay().getWidth();
 int height1 = wm1.getDefaultDisplay().getHeight();

 方法三：

 WindowManager manager = this.getWindowManager();
 DisplayMetrics outMetrics = new DisplayMetrics();
 manager.getDefaultDisplay().getMetrics(outMetrics);
 int width2 = outMetrics.widthPixels;
 int height2 = outMetrics.heightPixels;
 方法四：

 Resources resources = this.getResources();
 DisplayMetrics dm = resources.getDisplayMetrics();
 float density1 = dm.density;
 int width3 = dm.widthPixels;
 int height3 = dm.heightPixels;
 */

public class ScreenMetrics {
}
