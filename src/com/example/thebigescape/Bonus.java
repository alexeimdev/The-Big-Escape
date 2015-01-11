package com.example.thebigescape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bonus extends Object
{

	/** Constructor: **/
	public Bonus(int positionPointX, int positionPointY, String type,
			Context context, Canvas canvas, MainActivity mainActivity)
	{
		super(positionPointX, positionPointY, canvas, context, mainActivity);

		initImages();
		updateBodyBounds();
		setCurrentImageType(type);
	}

	/** Methods: **/
	public void initImages()
	{
		Bitmap bitmapArray[][] = new Bitmap[1][3];
		bitmapArray[0][0] = scaleBitmapSize(R.drawable.dollar);
		bitmapArray[0][1] = scaleBitmapSize(R.drawable.money_bag);
		bitmapArray[0][2] = scaleBitmapSize(R.drawable.diamond);

		images = bitmapArray;
	}

	protected void main()
	{
		blink(300);
	}

	public void setCurrentImageType(String type)
	{
		if (type.equals("dollar"))
		{
			setCurrendImageIndex(0);
		}
		else if (type.equals("money_bag"))
		{
			setCurrendImageIndex(1);
		}
		else if (type.equals("diamond"))
		{
			setCurrendImageIndex(2);
		}
	}

}
