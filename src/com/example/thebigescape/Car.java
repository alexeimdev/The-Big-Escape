package com.example.thebigescape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Car extends Object
{

	/** Constructor: **/
	public Car(int positionPointX, int positionPointY, Context context,
			Canvas canvas, MainActivity mainActivity, int direction)
	{
		super(positionPointX, positionPointY, canvas, context, mainActivity);

		this.direction = direction;

		initImages();
		updateBodyBounds();
	}

	/** Methods: **/
	public void initImages()
	{
		Bitmap bitmapArr[][] = new Bitmap[8][2];

		// NORTH:
		bitmapArr[0][0] = scaleBitmapSize(R.drawable.police_north_red);
		bitmapArr[0][1] = scaleBitmapSize(R.drawable.police_north_blue);
		// SOUTH:
		bitmapArr[1][0] = scaleBitmapSize(R.drawable.police_south_red);
		bitmapArr[1][1] = scaleBitmapSize(R.drawable.police_south_blue);
		// EAST:
		bitmapArr[2][0] = scaleBitmapSize(R.drawable.police_east_red);
		bitmapArr[2][1] = scaleBitmapSize(R.drawable.police_east_blue);
		// WEST:
		bitmapArr[3][0] = scaleBitmapSize(R.drawable.police_west_red);
		bitmapArr[3][1] = scaleBitmapSize(R.drawable.police_west_blue);

		images = bitmapArr;
	}

	protected void main()
	{
		currentImageIndex++;

		if (currentImageIndex > 1)
		{
			currentImageIndex = 0;
		}
		delay(500);
	}

}
