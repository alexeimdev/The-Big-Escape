package com.example.thebigescape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class StartupActivity extends Activity
{

	// Context:
	private Context context;

	// Level controller:
	private LevelController levelController;

	// Waiting time in seconds of this activity life cycle 
	private static long SLEEP_TIME = 1; // Sleep for some time

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Display:
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Context:
		context = this;

		setContentView(R.layout.splash);

		// Set and show layout: 
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}

	private class IntentLauncher extends Thread
	{
		public void run()
		{
			try
			{
				levelController = new LevelController(context);
				if (levelController.readFromFile() == 0) // game first time created
				{
					levelController.writeToFile(1); // open the 1st level
				}

				Thread.sleep(SLEEP_TIME * 1000); // run activity for SLEEP_TIME seconds
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			// Start MenuActivity:
			Intent intent = new Intent(StartupActivity.this, MenuActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			StartupActivity.this.finish();
		}
	}

}
