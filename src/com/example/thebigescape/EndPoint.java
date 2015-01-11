package com.example.thebigescape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class EndPoint extends Object
{

	/** Constructor: **/
	public EndPoint(int positionPointX, int positionPointY, Context context,
			Canvas canvas, MainActivity mainActivity)
	{
		super(positionPointX, positionPointY, canvas, context, mainActivity);

		initImages();
		updateBodyBounds();
	}

	/** Methods: **/
	public void initImages()
	{
		Bitmap bitmapArray[][] = new Bitmap[1][1];
		bitmapArray[0][0] = scaleBitmapSize(R.drawable.end_point);

		images = bitmapArray;
	}

}
