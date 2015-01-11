package com.example.thebigescape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Wall extends Object
{

	/** Constructor: **/
	public Wall(int bodyBoundsLEFT, int bodyBoundsTOP, int bodyBoundsRIGHT,
			int bodyBoundsBOTTOM, Context context, Canvas canvas,
			MainActivity mainActivity)
	{
		super(new Rect(bodyBoundsLEFT, bodyBoundsTOP, bodyBoundsRIGHT,
				bodyBoundsBOTTOM), canvas, context, mainActivity);
	}

	/** Methods: **/
	@Override
	protected void main()
	{
		delay(10);

		// Update position point:
		positionPointX = bodyBounds.left;
		positionPointY = bodyBounds.top;
	}

}
