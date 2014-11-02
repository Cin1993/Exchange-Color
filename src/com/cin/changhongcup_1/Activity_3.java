package com.cin.changhongcup_1;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class Activity_3 extends Activity{
	
	  private String pathName = "/sdcard/testimg.jpg";
		private Bitmap srcBitmap, bitmap;
		private ImageView dstimage;
		private Paint paint;


		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.third);
	        dstimage = (ImageView) findViewById(R.id.dstImageViewID);
	        srcBitmap = BitmapFactory.decodeFile(pathName);
	        //dstimage.setImageBitmap(srcBitmap);
			dstimage.setImageBitmap(oldRemeber(srcBitmap));
	 
}
		private Bitmap oldRemeber(Bitmap bmp)
		{
			int width = bmp.getWidth();
			int height = bmp.getHeight();
			Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
			int pixColor = 0;
			int pixR = 0;
			int pixG = 0;
			int pixB = 0;
			int newR = 0;
			int newG = 0;
			int newB = 0;
			int[] pixels = new int[width * height];
			bmp.getPixels(pixels, 0, width, 0, 0, width, height);
			for (int i = 0; i < height; i++)
			{
				for (int k = 0; k < width; k++)
				{
					pixColor = pixels[width * i + k];
					pixR = Color.red(pixColor);
					pixG = Color.green(pixColor);
					pixB = Color.blue(pixColor);
					newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
					newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
					newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
					int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
					pixels[width * i + k] = newColor;
				}
			}
			Paint paint = new Paint();
			bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
			Canvas canvas = new Canvas(bitmap);
			canvas.drawBitmap(bitmap, 0, 0,paint);
			return bitmap;
		}
}


