package com.cin.changhongcup_1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_4 extends Activity{
	
private Button button1;
private Button button2;
private int CAPTURE_CODE;
private Bitmap bm;
private ImageView iv;
private int IMAGE_CODE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forth);
		button1 = (Button)findViewById(R.id.buttonId_4_1);
		button2 = (Button)findViewById(R.id.button_4_2);
		
		ButtonListener1 buttonListener1 = new ButtonListener1();
		button1.setOnClickListener(buttonListener1);
		
		ButtonListener2 buttonListener2 = new ButtonListener2();
		button2.setOnClickListener(buttonListener2);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		try {
			ContentResolver resolver = getContentResolver();
			if (resultCode != RESULT_OK) {
			return;
			} else if (requestCode == IMAGE_CODE) {
			Uri originalUri = data.getData();
			if (originalUri != null) {
			bm = MediaStore.Images.Media.getBitmap(resolver,originalUri);
			iv.setImageBitmap(bm);
			}
			} else if (requestCode == CAPTURE_CODE && resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
			bm = (Bitmap) bundle.get("data");
			iv.setImageBitmap(bm);
			}
			}
			} catch (Exception e) {
			Toast.makeText(this, "选择图片错误，图片只能为jpg格式", Toast.LENGTH_SHORT).show();
			}
	}

	class ButtonListener1 implements OnClickListener{

	

		private static final int CAPTURE_CODE = 0;

		@Override
		public void onClick(View v) {
			Intent takephoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(takephoto,CAPTURE_CODE);
			Intent album = new Intent(Intent.ACTION_GET_CONTENT);
			album.setType("image/*");
			startActivityForResult(album, IMAGE_CODE);
		}
		
	}
	
	class ButtonListener2 implements OnClickListener{
		private static final int CAPTURE_CODE = 0;

		@Override
		public void onClick(View v) {
			Intent takephoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(takephoto,CAPTURE_CODE);
			Intent album = new Intent(Intent.ACTION_GET_CONTENT);
			album.setType("image/*");
			startActivityForResult(album, IMAGE_CODE);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
