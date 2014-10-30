package com.cin.changhongcup_1;

import com.cin.changhongcup_1.MainActivityActivity.ButtonListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_2 extends Activity{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private Button button1;
	private Button button2;
	private Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		button1 = (Button)findViewById(R.id.buttonId_1);
		button2 = (Button)findViewById(R.id.buttonId_2);
		button3 = (Button)findViewById(R.id.buttonId_3);
		
		ButtonListener buttonlistener1 = new ButtonListener();
		button1.setOnClickListener(buttonlistener1);
		
		ButtonListener2 buttonlistener2 = new ButtonListener2();
		button2.setOnClickListener(buttonlistener2);
		
		ButtonListener3 buttonlistener3 =new ButtonListener3();
		button3.setOnClickListener(buttonlistener3);
	}
	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Activity_2.this, Activity_3.class);
			startActivity(intent);
				
		}
	}
	class ButtonListener2 implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Activity_2.this, Activity_4.class);
			startActivity(intent);
		}
	}
	class ButtonListener3 implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Activity_2.this, Activity_5.class);
			startActivity(intent);
		}
		
	}
}
