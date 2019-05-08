

Bitmap类：

``` 
public static Bitmap createBitmap(@NonNull Bitmap src) {
    return createBitmap(src, 0, 0, src.getWidth(), src.getHeight());
}

public static Bitmap createBitmap(int width, int height, @NonNull Config config) {
    return createBitmap(width, height, config, true);
}
```
Canvas类：

``` 
public void drawBitmap(@NonNull Bitmap bitmap, float left, float top, @Nullable Paint paint) {
    super.drawBitmap(bitmap, left, top, paint);
}
```

Android截图

# 使用DrawingCache

```
@SuppressWarnings("deprecation")
private Bitmap screenshot1(View view) {
	//启用DrawingCache并创建位图
	view.setDrawingCacheEnabled(true);
	view.buildDrawingCache();
	//创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
	Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
	//禁用DrawingCahce否则会影响性能
	view.setDrawingCacheEnabled(false);
	return bitmap;
}
```

# 直接调用View.draw

```
private Bitmap screenshot2(View view) {
	Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
			Bitmap.Config.ARGB_8888);
	bitmap.setDensity(getResources().getDisplayMetrics().densityDpi);
	Canvas canvas = new Canvas(bitmap);
	//把view中的内容绘制在画布上
	view.draw(canvas);
	canvas.setBitmap(null);
	return bitmap;
}
```

# 高德地图截图

[地图截图功能](https://lbs.amap.com/dev/demo/screenshot-mapview-view#Android)

``` 
/**
 * 组装地图截图和其他View截图，需要注意的是目前提供的方法限定为MapView与其他View在同一个ViewGroup下
 *@param    bitmap             地图截图回调返回的结果
 *@param   viewContainer      MapView和其他要截图的View所在的父容器ViewGroup
 *@param   mapView            MapView控件
 *@param   views              其他想要在截图中显示的控件
 * */
public static Bitmap getMapAndViewScreenShot(Bitmap bitmap, ViewGroup viewContainer, MapView mapView, View...views){
    int width = viewContainer.getWidth();
    int height = viewContainer.getHeight();
    final Bitmap screenBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(screenBitmap);
    canvas.drawBitmap(bitmap, mapView.getLeft(), mapView.getTop(), null);
    for (View view:views){
        view.setDrawingCacheEnabled(true);
        canvas.drawBitmap(view.getDrawingCache(), view.getLeft(), view.getTop(), null);
    }

    return screenBitmap;
}
```















