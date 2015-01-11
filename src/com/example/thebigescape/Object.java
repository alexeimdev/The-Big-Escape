package com.example.thebigescape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Object extends Thread
{

	/** Variables: **/
	// Thread:
	protected boolean isAlive = true;
	protected boolean isRunning = true;

	// Body bounds dimensions:
	protected Rect bodyBounds;

	// Speed:
	protected int speedHorizenal = 0;
	protected int speedVertical = 0;
	protected int speed = 0;

	// Location:
	protected int positionPointX = 0;
	protected int positionPointY = 0;

	// Images:
	protected Bitmap images[][];
	protected int currentImageIndex = 0;

	// Canvas:
	protected Canvas canvas;

	// Context:
	Context context;

	// Draw:
	protected boolean isVisible = true;
	protected boolean isBlinking = false;

	// Direction:
	protected int direction = 0;

	// Collision:
	protected int collisionSide = 0;

	// Main Activity:
	protected MainActivity mainActivity;

	/** Constructors: **/
	// Moving Object:
	public Object(int positionPointX, int positionPointY, Canvas canvas,
			Context context, MainActivity mainActivity)
	{
		this.positionPointX = positionPointX;
		this.positionPointY = positionPointY;
		this.canvas = canvas;
		this.context = context;
		this.bodyBounds = new Rect(0, 0, 0, 0);
		this.mainActivity = mainActivity;

		// for debug proposes:
		this.setName(this.getClass().getSimpleName());
	}

	// Static Object:
	public Object(Rect bodyBounds, Canvas canvas, Context context,
			MainActivity mainActivity)
	{
		this.positionPointX = bodyBounds.left;
		this.positionPointY = bodyBounds.top;
		this.bodyBounds = bodyBounds;
		this.canvas = canvas;
		this.context = context;
		this.mainActivity = mainActivity;

		// for debug proposes:
		this.setName(this.getClass().getSimpleName());
	}

	/** Methods: **/
	protected void drawImage()
	{
		if (isVisible)
		{
			canvas.drawBitmap(images[direction][currentImageIndex],
					positionPointX, positionPointY, null);
		}
	}

	public void drawRectangle()
	{
		if (isVisible)
		{
			Paint paint = new Paint();
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(bodyBounds.left, bodyBounds.top, bodyBounds.right,
					bodyBounds.bottom, paint);
		}
	}

	@Override
	public void run()
	{
		super.run();

		while (this.isAlive)
		{
			if (this.isRunning)
			{
				main();
			}
		}
	}

	// Override it:
	protected void main()
	{
	}

	// Collision methods:
	private void checkCollisionSide(Rect self, Rect other)
	{
		int offset = 5;
		Rect otherRectLeft = new Rect(other.left - offset, other.top - offset,
				other.left + offset, other.bottom + offset);
		Rect otherRectTop = new Rect(other.left - offset, other.top - offset,
				other.right + offset, other.top + offset);
		Rect otherRectRight = new Rect(other.right - offset,
				other.top - offset, other.right + offset, other.bottom + offset);
		Rect otherRectBottom = new Rect(other.left - offset, other.bottom
				- offset, other.right + offset, other.bottom + offset);

		if (Rect.intersects(self, otherRectBottom))
		{
			if (direction == Direction.NORTH
					|| direction == Direction.NORTH_EAST
					|| direction == Direction.NORTH_WEST)
			{
				collisionSide = Collision.TOP;
			}
		}
		else if (Rect.intersects(self, otherRectTop))
		{
			if (direction == Direction.SOUTH
					|| direction == Direction.SOUTH_EAST
					|| direction == Direction.SOUTH_WEST)
			{
				collisionSide = Collision.BOTTOM;
			}
		}
		else if (Rect.intersects(self, otherRectLeft))
		{
			if (direction == Direction.EAST
					|| direction == Direction.SOUTH_EAST
					|| direction == Direction.NORTH_EAST)
			{
				collisionSide = Collision.RIGHT;
			}
		}
		else if (Rect.intersects(self, otherRectRight))
		{
			if (direction == Direction.WEST
					|| direction == Direction.NORTH_WEST
					|| direction == Direction.SOUTH_WEST)
			{
				collisionSide = Collision.LEFT;
			}
		}
		else
		{
			collisionSide = Collision.NO;
		}
	}

	protected boolean collidedWith(Object obj1)
	{
		Rect r1 = new Rect(positionPointX, positionPointY, positionPointX
				+ bodyBounds.width(), positionPointY + bodyBounds.height());
		Rect r2 = new Rect(obj1.positionPointX, obj1.positionPointY,
				obj1.positionPointX + obj1.bodyBounds.width(),
				obj1.positionPointY + obj1.bodyBounds.height());

		if (Rect.intersects(r1, r2) && obj1.isVisible)
		{
			checkCollisionSide(r1, r2);
			return true;
		}
		return false;
	}

	protected void updateBodyBounds()
	{
		bodyBounds = new Rect(0, 0,
				images[direction][currentImageIndex].getWidth(),
				images[direction][currentImageIndex].getHeight());
	}

	protected void delay(int milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	protected void blink(int milliSeconds)
	{
		isVisible = false;
		delay(milliSeconds);
		isVisible = true;
		delay(milliSeconds);
	}

	protected Bitmap scaleBitmapSize(int resourcePath)
	{
		Bitmap originalBitmap = BitmapFactory.decodeResource(
				context.getResources(), resourcePath);

		double width = (double) originalBitmap.getWidth() * 0.8;
		double height = (double) originalBitmap.getHeight() * 0.8;

		Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				context.getResources(), resourcePath), (int) width,
				(int) height, false);
		return bitmap;
	}

	/** Getters and Setters: **/
	public boolean getIsAlive()
	{
		return isAlive;
	}

	public void setIsAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}

	public boolean getIsRunning()
	{
		return isRunning;
	}

	public void setIsRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

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

	public void setPositionPointY(int positionPointX)
	{
		this.positionPointY = positionPointX;
	}

	public int getCurrendImageIndex()
	{
		return currentImageIndex;
	}

	public void setCurrendImageIndex(int currentImageIndex)
	{
		this.currentImageIndex = currentImageIndex;
	}

}
