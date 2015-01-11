package com.example.thebigescape;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackgroundImage
{

	/** Variables: **/
	private int positionPointX;
	private int positionPointY;
	private Canvas canvas;
	private Bitmap bitmap;

	/** Constructor: **/
	public BackgroundImage(int positionPointX, int positionPointY,
			Canvas canvas, Bitmap bitmap)
	{
		this.positionPointX = positionPointX;
		this.positionPointY = positionPointY;
		this.canvas = canvas;
		this.bitmap = bitmap;
	}

	/** Methods: **/
	protected void drawImage()
	{
		canvas.drawBitmap(bitmap, positionPointX, positionPointY, null);
	}

	/** Getters and Setters: **/
	public int getPositionPointX()
	{
		return positionPointX;
	}

	public void setPositionPointX(int positionPointX)
	{
		this.positionPointX = positionPointX;
	}

	public int getPositionPointY()
	{
		return positionPointY;
	}

	public void setPositionPointY(int positionPointY)
	{
		this.positionPointY = positionPointY;
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
	}

	public int getTOP()
	{
		return positionPointY;
	}

	public int getLEFT()
	{
		return positionPointX;
	}

	public int getRIGHT()
	{
		return positionPointX + bitmap.getWidth();
	}

	public int getBOTTOM()
	{
		return positionPointY + bitmap.getHeight();
	}

}
