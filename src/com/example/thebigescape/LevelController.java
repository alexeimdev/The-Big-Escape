package com.example.thebigescape;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.content.Context;
import android.util.Log;

public class LevelController
{

	/** Variables: **/
	private static final String fileName = "unlockedLevels.txt";
	private Context context;

	/** Constructor: **/
	public LevelController(Context context)
	{
		this.context = context;
	}

	/** Methods: **/
	public void writeToFile(int data)
	{
		try
		{
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					context.openFileOutput(fileName, Context.MODE_PRIVATE));
			outputStreamWriter.write("" + data);
			outputStreamWriter.close();
		}
		catch (IOException e)
		{
			Log.e("Exception", "File write failed: " + e.toString());
		}
	}

	public int readFromFile()
	{
		String ret = "";
		try
		{
			InputStream inputStream = context.openFileInput(fileName);

			if (inputStream != null)
			{
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null)
				{
					stringBuilder.append(receiveString);
				}
				inputStream.close();
				ret = stringBuilder.toString();
			}
		}
		catch (FileNotFoundException e)
		{
			Log.e("login activity", "File not found: " + e.toString());
		}
		catch (IOException e)
		{
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		if (ret.equalsIgnoreCase(""))
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(ret);
		}
	}

}
