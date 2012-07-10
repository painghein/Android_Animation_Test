package com.gajah.stars;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

public class StarAnimationActivity extends Activity {
	/** Called when the activity is first created. */
	Animation[] animations = new Animation[10];
	int btnImgIds[] = { R.drawable.menu_btn, R.drawable.lullaby_15,
			R.drawable.lullaby_30, R.drawable.lullaby_45, R.drawable.lullaby_60 };
	int timeArray[] = { 15000, 15000, 30000, 45000, 60000 };
	AbsoluteLayout layout;
	Button btnTimer;
	int index = 0;
	MediaPlayer mediaPlayer;

	Runnable btnImageRunner = new Runnable() {
		@Override
		public void run() {
			btnTimer.setBackgroundResource(btnImgIds[0]);
		}

	};

	Handler timerHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1)
				finish();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		layout = (AbsoluteLayout) findViewById(R.id.main_layout);
		btnTimer = (Button) findViewById(R.id.button1);

		animations[0] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation);
		animations[1] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_1);
		animations[2] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_2);
		animations[3] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_3);
		animations[4] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_4);
		animations[5] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_5);
		animations[6] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_6);
		animations[7] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_7);
		animations[8] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_8);
		animations[9] = AnimationUtils.loadAnimation(this,
				R.anim.star_animation_9);

		for (int i = 0; i < layout.getChildCount(); i++) {
			ImageView img = (ImageView) layout.getChildAt(i);
			img.startAnimation(animations[i % animations.length]);
		}

		btnTimer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btnTimer.removeCallbacks(btnImageRunner);
				index++;
				if (index == btnImgIds.length)
					index = 1;
				btnTimer.setBackgroundResource(btnImgIds[index]);

				// send message again
				timerHandler.removeMessages(1);
				timerHandler.sendEmptyMessageDelayed(1, timeArray[index]);

				// change background
				btnTimer.postDelayed(btnImageRunner, 2000);

			}

		});

		mediaPlayer = MediaPlayer.create(this, R.raw.lullaby_combined);
		mediaPlayer.setScreenOnWhilePlaying(true);
		mediaPlayer.start(); // no need to call prepare(); create() does that
								// for you

		timerHandler.sendEmptyMessageDelayed(1, timeArray[index]);

	}

	@Override
	public void onResume() {
		super.onResume();
		if (!mediaPlayer.isPlaying())
			mediaPlayer.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mediaPlayer.stop();
		mediaPlayer.release();
	}
}

//
// ImageView img1 = (ImageView)findViewById(R.id.ImageView01);
// img1.startAnimation(animations[0]);
//
// ImageView img2 = (ImageView)findViewById(R.id.ImageView02);
// img2.startAnimation(animations[1]);
//
// ImageView img3 = (ImageView)findViewById(R.id.ImageView03);
// img3.startAnimation(animations[2]);
//
// ImageView img4 = (ImageView)findViewById(R.id.ImageView04);
// img4.startAnimation(animations[3]);
//
// ImageView img5 = (ImageView)findViewById(R.id.ImageView05);
// img5.startAnimation(animations[4]);
//
// ImageView img6 = (ImageView)findViewById(R.id.ImageView06);
// img6.startAnimation(animations[5]);
//
// ImageView img7 = (ImageView)findViewById(R.id.ImageView07);
// img7.startAnimation(animations[6]);
//
// ImageView img8 = (ImageView)findViewById(R.id.ImageView08);
// img8.startAnimation(animations[7]);
//
// ImageView img9 = (ImageView)findViewById(R.id.ImageView09);
// img9.startAnimation(animations[8]);
//
// ImageView img10 = (ImageView)findViewById(R.id.ImageView10);
// img10.startAnimation(animations[9]);
//
// ImageView img11 = (ImageView)findViewById(R.id.ImageView11);
// img11.startAnimation(animations[8]);
//
// ImageView img12 = (ImageView)findViewById(R.id.ImageView12);
// img12.startAnimation(animations[7]);
//
// ImageView img13 = (ImageView)findViewById(R.id.ImageView13);
// img13.startAnimation(animations[6]);
//
// ImageView img14 = (ImageView)findViewById(R.id.ImageView14);
// img14.startAnimation(animations[5]);
//
// ImageView img15 = (ImageView)findViewById(R.id.ImageView15);
// img15.startAnimation(animations[4]);
//
// ImageView img16 = (ImageView)findViewById(R.id.ImageView16);
// img16.startAnimation(animations[3]);
//
// ImageView img17 = (ImageView)findViewById(R.id.ImageView17);
// img17.startAnimation(animations[2]);
//
// ImageView img18 = (ImageView)findViewById(R.id.ImageView18);
// img18.startAnimation(animations[1]);
//
// ImageView img19 = (ImageView)findViewById(R.id.ImageView19);
// img19.startAnimation(animations[2]);
//
// ImageView img20 = (ImageView)findViewById(R.id.ImageView20);
// img20.startAnimation(animations[1]);
//
// ImageView img21 = (ImageView)findViewById(R.id.ImageView21);
// img21.startAnimation(animations[0]);
//
// ImageView img22 = (ImageView)findViewById(R.id.ImageView22);
// img22.startAnimation(animations[1]);
//
// ImageView img23 = (ImageView)findViewById(R.id.ImageView23);
// img23.startAnimation(animations[2]);
//
// ImageView img24 = (ImageView)findViewById(R.id.ImageView24);
// img24.startAnimation(animations[3]);

// DisplayMetrics displaymetrics = new DisplayMetrics();
// getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
// final int height = displaymetrics.heightPixels;
// final int width = displaymetrics.widthPixels;
// Random r = new Random();

// create image view and place it all over main layout
// for (int i = 0; i < 10; i++) {
// ImageView img = new ImageView(this);
// img.setImageResource(R.drawable.star_scaled);
// imageViews[i]=img;
//
//
// }

// for(int i=0; i<imageViews.length;i++){
// final int counter = i;
// final LayoutParams params = new
// LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
// params.leftMargin= r.nextInt(width);
// params.topMargin = r.nextInt(height);
// mainLayout.addView(imageViews[counter], params);
// imageViews[counter].startAnimation(animations[counter]);
// // mainLayout.postDelayed(new Runnable(){
// // @Override
// // public void run() {
// // }
// //
// // }, r.nextInt(10000-5000)+5000);
//
//
// }