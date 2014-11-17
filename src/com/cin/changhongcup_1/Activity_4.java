package com.cin.changhongcup_1;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_4 extends Activity{
	private final String IMAGE_TYPE = "image/*";
	private final int IMAGE_CODE = 0;  								//该值随意取
	private Button pbutton_1 = null, pbutton_2 = null;
	private ImageView imageShow_1 = null;
	private ImageView imageShow_2 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forth);
		pbutton_1 = (Button)findViewById(R.id.buttonId_4_1);
		pbutton_2 = (Button)findViewById(R.id.buttonId_4_2);
		imageShow_1 = (ImageView)findViewById(R.id.imageShow_1);
		imageShow_2 = (ImageView)findViewById(R.id.imageShow_2);
		
		pbutton_1.setOnClickListener(listener_1);
		pbutton_2.setOnClickListener(listener_2);
	}
	private OnClickListener listener_1 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Button btn = (Button) v;
			switch(btn.getId())
			{
			case R.id.buttonId_4_1:
				setImage();
				break;
			case R.id.buttonId_4_2:
				setImage();
				break;
			}
		}
		private void setImage()
		{
			Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
			getAlbum.setType(IMAGE_TYPE);
			startActivityForResult(getAlbum, IMAGE_CODE);
		}
	};
	
private OnClickListener listener_2 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Button btn = (Button) v;
			switch(btn.getId())
			{
			case R.id.buttonId_4_1:
				setImage();
				break;
			case R.id.buttonId_4_2:
				setImage();
				break;
			}
		}
		private void setImage()
		{
			Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
			getAlbum.setType(IMAGE_TYPE);
			startActivityForResult(getAlbum, IMAGE_CODE);
		}
	};
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode != RESULT_OK)
		{
			Log.e("TAG->onresult","ActivityResult resultCode error");
			return;
		}
		Bitmap bm = null;
		ContentResolver resolver = getContentResolver();
		
		if(requestCode == IMAGE_CODE)
		{
			try{
				Uri originalUri = data.getData();
				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
				imageShow_1.setImageBitmap(bm);
				
				String[] proj = {MediaStore.Images.Media.DATA};
				Cursor cursor = managedQuery(originalUri, proj, null, null, null);
				
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
             }catch (IOException e) {

                 Log.e("TAG-->Error",e.toString()); 
			}finally{		
			};
		}
	};
}
