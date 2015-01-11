package com.example.thebigescape;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener
{

	/** Variables: **/
	// Context:
	private Context context;

	// Level controller:
	private LevelController levelController;

	// Level:
	private int unlockedLevels = 1;

	// Buttons:
	private Button btnLevelArray[] = new Button[10];
	private Button btnExit;

	// Music:
	private MediaPlayer mediaPlayer;

	/** Methods: **/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Display:
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

		// Context:
		context = this;

		setContentView(R.layout.level_menu);

		// Music:
		mediaPlayer = MediaPlayer.create(context, R.raw.game_startup_music);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();

		// Level controller:
		levelController = new LevelController(context);
		unlockedLevels = levelController.readFromFile();

		btnLevelArray[0] = (Button) findViewById(R.id.level01);
		btnLevelArray[1] = (Button) findViewById(R.id.level02);
		btnLevelArray[2] = (Button) findViewById(R.id.level03);
		btnLevelArray[3] = (Button) findViewById(R.id.level04);
		btnLevelArray[4] = (Button) findViewById(R.id.level05);
		btnLevelArray[5] = (Button) findViewById(R.id.level06);
		btnLevelArray[6] = (Button) findViewById(R.id.level07);
		btnLevelArray[7] = (Button) findViewById(R.id.level08);
		btnLevelArray[8] = (Button) findViewById(R.id.level09);
		btnLevelArray[9] = (Button) findViewById(R.id.level10);

		btnExit = (Button) findViewById(R.id.EXIT);

		for (int i = 0; i < 10; i++)
		{
			if (i < unlockedLevels)
			{
				btnLevelArray[i].setEnabled(true);
			}
			else
			{
				btnLevelArray[i].setEnabled(false);
			}
		}

		// Set Buttons's Listeners:
		btnLevelArray[0].setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				if (1 <= unlockedLevels)
				{
					gotoMainActivity(1);
				}
			}
		});

		btnLevelArray[1].setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				if (2 <= unlockedLevels)
				{
					gotoMainActivity(2);
				}
			}
		});

		btnLevelArray[2].setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				if (3 <= unlockedLevels)
				{
					gotoMainActivity(3);
				}
			}
		});

		btnLevelArray[3].setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				if (4 <= unlockedLevels)
				{
					gotoMainActivity(4);
				}
			}
		});

		btnLevelArray[4].setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				if (4 <= unlockedLevels)
				{
					gotoMainActivity(5);
				}
			}
		});

		btnExit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						MenuActivity.this);
				dialog.setMessage("Are you sure you want to exit game?");
				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								finish();
								System.exit(0);
								int pid = android.os.Process.myPid();
								android.os.Process.killProcess(pid);
							}
						});

				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{

							}
						});

				AlertDialog alertDialog = dialog.create();
				alertDialog.show();
			}
		});

	}

	public void onPause()
	{
		super.onPause();
		mediaPlayer.pause();
	}

	public void onResume()
	{
		super.onResume();
		mediaPlayer.start();
	}

	public void onBackPressed()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(MenuActivity.this);
		dialog.setMessage("Are you sure you want to exit game?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				finish();
				System.exit(0);
				int pid = android.os.Process.myPid();
				android.os.Process.killProcess(pid);
			}
		});

		dialog.setNegativeButton("No", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
			}
		});
		AlertDialog alertDialog = dialog.create();
		alertDialog.show();
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1)
	{
		// TODO Auto-generated method stub
	}

	public void gotoMainActivity(int chosenLevel)
	{
		// Start MainActivity:
		Intent intent = new Intent(MenuActivity.this, MainActivity.class);
		intent.putExtra("chosenLevel", chosenLevel);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		MenuActivity.this.finish();
		mediaPlayer.stop();
	}

}