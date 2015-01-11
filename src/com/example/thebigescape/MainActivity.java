package com.example.thebigescape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener,
		OnTouchListener
{

	/** Variables: **/
	// Context:
	private Context context;

	// Canvas:
	private Canvas canvas;

	// View:
	private ImageView imageView;

	// Level:
	private Level level;

	// Display:
	public int screenRIGHT = 0;
	public int screenLEFT = 0;
	public int screenTOP = 0;
	public int screenBOTTOM = 0;

	public int screenWIDTH = 0;
	public int screenHEIGHT = 0;

	// Sensor Manager:
	private SensorManager sensorManager;
	private Sensor accelerometer;

	// Accelerometer:
	public static int accelerometerCoordinateX;
	public static int accelerometerCoordinateY;

	// Music:
	public MediaPlayer mediaPlayer;

	// First Call of onResume indicator:
	public boolean onResumeFirstCall = true;

	/** Methods: **/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);

		// Context:
		context = this;

		// Display:
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWIDTH = screenRIGHT = size.x;
		screenHEIGHT = screenBOTTOM = size.y;

		// Sensor:
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// Basic Surface Bitmap:
		Bitmap pallet = Bitmap.createBitmap(screenWIDTH, screenHEIGHT,
				Bitmap.Config.ARGB_8888);

		// Canvas:
		canvas = new Canvas(pallet);

		// View:
		imageView = new ImageView(this);
		imageView.setOnTouchListener(this);
		imageView.setImageBitmap(pallet);
		setContentView(imageView);

		// Level:
		level = new Level(context, imageView, canvas, this, getIntent()
				.getExtras().getInt("chosenLevel"));
	}

	@Override
	protected void onResume()
	{
		System.out.println("onResume");
		super.onResume();

		if (onResumeFirstCall)
		{
			onResumeFirstCall = false;
			level.start();
		}
		level.resume();

		mediaPlayer.start();

		// Accelerometer ON:
		sensorManager.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause()
	{
		System.out.println("onPause");
		super.onPause();

		sensorManager.unregisterListener(this); // Accelerometer OFF
		mediaPlayer.pause();
		level.pause();
	}

	@Override
	protected void onDestroy()
	{
		System.out.println("onDestroy");
		super.onDestroy();

		level.destroy();
		level = null;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1)
	{
		onPause();

		final Dialog dialog;
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_main_menu);
		dialog.show();

		Button btnContinue = (Button) dialog.findViewById(R.id.btn1_main_menu);
		Button btnReplay = (Button) dialog.findViewById(R.id.btn2_main_menu);
		Button btnLevelSelect = (Button) dialog
				.findViewById(R.id.btn3_main_menu);
		Button btnHelp = (Button) dialog.findViewById(R.id.btn4_main_menu);

		btnContinue.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
				onResume();
			}
		});

		btnReplay.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				System.out.println("replay is pressed");
				dialog.dismiss();
				AlertDialog.Builder dialog = new AlertDialog.Builder(context);
				dialog.setMessage("Are you sure you want to replay level?");
				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								level.restart();
								onResume();
							}
						});

				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								onResume();
							}
						});

				AlertDialog alertDialog = dialog.create();
				alertDialog.show();

			}
		});

		btnLevelSelect.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				dialog.dismiss();
				AlertDialog.Builder dialog = new AlertDialog.Builder(context);
				dialog.setMessage("Are you sure you want to exit level?");
				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								gotoMenuActivity();
							}
						});

				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								onResume();
							}
						});

				AlertDialog alertDialog = dialog.create();
				alertDialog.show();
			}
		});

		btnHelp.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				AlertDialog.Builder dialog = new AlertDialog.Builder(context);
				dialog.setTitle("Instructions");

				String message = "1. you can move the player by tilting\n"
						+ "    the mobile phone verticaly and horizenly.\n"
						+ "2. the angle of the tilt effects the speed\n"
						+ "    of the player movment.\n"
						+ "3. get the key or keys and avoid cought by the cops.\n"
						+ "4. reach the destenation with the keys before time ends. \n"
						+ "5. after you got the key or keys, search the exit.\n"
						+ "6. try to collect as meny bounes as you can.\n"
						+ "7. you got several tries you secced each level.";

				dialog.setMessage(message);

				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								dialog.dismiss();
							}
						});

				AlertDialog alertDialog = dialog.create();
				alertDialog.show();
			}
		});

		dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
		{
			@Override
			public void onCancel(DialogInterface dialog)
			{
				onResume();
			}
		});

		return false;
	}

	public void onBackPressed()
	{
		onResume();
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		int limit = 3;

		if ((int) event.values[1] > limit)
		{
			accelerometerCoordinateX = limit;
			return;
		}
		if ((int) event.values[0] > limit)
		{
			accelerometerCoordinateY = limit;
			return;
		}
		{
			accelerometerCoordinateX = (int) event.values[1];
			accelerometerCoordinateY = (int) event.values[0];
			return;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1)
	{
	}

	public void gotoMenuActivity()
	{
		// Start MenuActivity:
		Intent intent = new Intent(MainActivity.this, MenuActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		this.finish();
	}

}
