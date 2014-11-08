package com.cin.changhongcup_1;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Activity_3 extends Activity{
	
	    private String pathName = "/sdcard/testimg.jpg";
		private Bitmap srcBitmap;
		private ImageView dstimage;
		private RadioGroup radioGroup;
		private RadioButton radioButton_0;
		private RadioButton radioButton_1;
		private RadioButton radioButton_2;
		private RadioButton radioButton_3;
		
		//怀旧效果
		public Bitmap oldRemeber(Bitmap bmp)
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
		
		//LOMO效果
		public static Bitmap lomoFilter(Bitmap bitmap) {
	        int width = bitmap.getWidth();
	        int height = bitmap.getHeight();
	        int dst[] = new int[width * height];
	        bitmap.getPixels(dst, 0, width, 0, 0, width, height);

	        int ratio = width > height ? height * 32768 / width : width * 32768 / height;
	        int cx = width >> 1;
	        int cy = height >> 1;
	        int max = cx * cx + cy * cy;
	        int min = (int) (max * (1 - 0.8f));
	        int diff = max - min;

	        int ri, gi, bi;
	        int dx, dy, distSq, v;

	        int R, G, B;

	        int value;
	        int pos, pixColor;
	        int newR, newG, newB;
	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                pos = y * width + x;
	                pixColor = dst[pos];
	                R = Color.red(pixColor);
	                G = Color.green(pixColor);
	                B = Color.blue(pixColor);

	                value = R < 128 ? R : 256 - R;
	                newR = (value * value * value) / 64 / 256;
	                newR = (R < 128 ? newR : 255 - newR);

	                value = G < 128 ? G : 256 - G;
	                newG = (value * value) / 128;
	                newG = (G < 128 ? newG : 255 - newG);

	                newB = B / 2 + 0x25;

	                // ==========边缘黑暗==============//
	                dx = cx - x;
	                dy = cy - y;
	                if (width > height)
	                    dx = (dx * ratio) >> 15;
	                else
	                    dy = (dy * ratio) >> 15;

	                distSq = dx * dx + dy * dy;
	                if (distSq > min) {
	                    v = ((max - distSq) << 8) / diff;
	                    v *= v;

	                    ri = newR * v >> 16;
	                    gi = newG * v >> 16;
	                    bi = newB * v >> 16;

	                    newR = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
	                    newG = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
	                    newB = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
	                }
	                // ==========边缘黑暗end==============//

	                dst[pos] = Color.rgb(newR, newG, newB);
	            }
	        }

	        Bitmap acrossFlushBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	        acrossFlushBitmap.setPixels(dst, 0, width, 0, 0, width, height);
	        return acrossFlushBitmap;
	    }

		
		//暖意效果
		 public static Bitmap warmthFilter(Bitmap bmp, int centerX, int centerY) {
		        final int width = bmp.getWidth();
		        final int height = bmp.getHeight();
		        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

		        int pixR = 0;
		        int pixG = 0;
		        int pixB = 0;

		        int pixColor = 0;

		        int newR = 0;
		        int newG = 0;
		        int newB = 0;
		        int radius = Math.min(centerX, centerY);

		        final float strength = 150F; // 光照强度 100~150
		        int[] pixels = new int[width * height];
		        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		        int pos = 0;
		        for (int i = 1, length = height - 1; i < length; i++) {
		            for (int k = 1, len = width - 1; k < len; k++) {
		                pos = i * width + k;
		                pixColor = pixels[pos];

		                pixR = Color.red(pixColor);
		                pixG = Color.green(pixColor);
		                pixB = Color.blue(pixColor);

		                newR = pixR;
		                newG = pixG;
		                newB = pixB;

		                // 计算当前点到光照中心的距离，平面座标系中求两点之间的距离
		                int distance = (int) (Math.pow((centerY - i), 2) + Math.pow(centerX - k, 2));
		                if (distance < radius * radius) {
		                    // 按照距离大小计算增加的光照值
		                    int result = (int) (strength * (1.0 - Math.sqrt(distance) / radius));
		                    newR = pixR + result;
		                    newG = pixG + result;
		                    newB = pixB + result;
		                }

		                newR = Math.min(255, Math.max(0, newR));
		                newG = Math.min(255, Math.max(0, newG));
		                newB = Math.min(255, Math.max(0, newB));

		                pixels[pos] = Color.argb(255, newR, newG, newB);
		            }
		        }

		        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		        return bitmap;
		    }



		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.third);
	        dstimage = (ImageView) findViewById(R.id.dstImageViewID);
	        srcBitmap = BitmapFactory.decodeFile(pathName);
	        
	        radioGroup = (RadioGroup)findViewById(R.id.radioGroupID_1);
	        radioButton_0 = (RadioButton)findViewById(R.id.radioButtonID_0);
		    radioButton_1 = (RadioButton)findViewById(R.id.radioButtonID_1);
		    radioButton_2 = (RadioButton)findViewById(R.id.radioButtonID_2);
		    radioButton_3 = (RadioButton)findViewById(R.id.radioButtonID_3);
			radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					if(checkedId == radioButton_0.getId())
					{
						dstimage.setImageBitmap(srcBitmap);
					}
					else if(checkedId == radioButton_1.getId())
					{
						dstimage.setImageBitmap(oldRemeber(srcBitmap));
					}
					else if(checkedId == radioButton_2.getId())
					{
						 dstimage.setImageBitmap(lomoFilter(srcBitmap));
					}
					else if (checkedId == radioButton_3.getId())
					{
						dstimage.setImageBitmap(warmthFilter(srcBitmap, 200, 300));
					}
				}
			});
			}

		
}


