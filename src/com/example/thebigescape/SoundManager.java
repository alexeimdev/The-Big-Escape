package com.example.thebigescape;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager
{

	/** Variables: **/
	private Context context;
	private SoundPool sonudPool;

	/** Constructor: **/
	public SoundManager(Context context)
	{
		sonudPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
		this.context = context;
	}

	/** Methods: **/
	// Load up a sound:
	public int load(int soundId)
	{
		return sonudPool.load(context, soundId, 1);
	}

	// Play a sound:
	public void play(int soundId)
	{
		sonudPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
	}

	// Play a sound repeated
	public void loop(int soundId, int loopNumber)
	{
		sonudPool.play(soundId, 1.0f, 1.0f, 1, loopNumber, 1.0f);
	}

	// Free all the sounds:
	public void unloadAll()
	{
		sonudPool.release();
	}

}
