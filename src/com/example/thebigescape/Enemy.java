package com.example.thebigescape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Enemy extends Object
{

	/** Variables: **/
	// Level:
	Level level;

	/** Constructor: **/
	public Enemy(int positionPointX, int positionPointY, int speedHorizenal,
			int speedVertical, Context context, Canvas canvas,
			MainActivity mainActivity, Level level)
	{
		super(positionPointX, positionPointY, canvas, context, mainActivity);

		this.context = context;
		this.speedHorizenal = speedHorizenal;
		this.speedVertical = speedVertical;
		this.level = level;

		initImages();
		updateBodyBounds();
	}

	/** Methods: **/
	private void initImages()
	{
		Bitmap bitmapArray[][] = new Bitmap[8][4];

		// NORTH:
		bitmapArray[0][0] = scaleBitmapSize(R.drawable.cop_north);
		bitmapArray[0][1] = scaleBitmapSize(R.drawable.cop_north_right);
		bitmapArray[0][2] = scaleBitmapSize(R.drawable.cop_north);
		bitmapArray[0][3] = scaleBitmapSize(R.drawable.cop_north_left);
		// SOUTH:
		bitmapArray[1][0] = scaleBitmapSize(R.drawable.cop_south);
		bitmapArray[1][1] = scaleBitmapSize(R.drawable.cop_south_right);
		bitmapArray[1][2] = scaleBitmapSize(R.drawable.cop_south);
		bitmapArray[1][3] = scaleBitmapSize(R.drawable.cop_south_left);
		// EAST:
		bitmapArray[2][0] = scaleBitmapSize(R.drawable.cop_east);
		bitmapArray[2][1] = scaleBitmapSize(R.drawable.cop_east_right);
		bitmapArray[2][2] = scaleBitmapSize(R.drawable.cop_east);
		bitmapArray[2][3] = scaleBitmapSize(R.drawable.cop_east_left);
		// WEST:
		bitmapArray[3][0] = scaleBitmapSize(R.drawable.cop_west);
		bitmapArray[3][1] = scaleBitmapSize(R.drawable.cop_west_right);
		bitmapArray[3][2] = scaleBitmapSize(R.drawable.cop_west);
		bitmapArray[3][3] = scaleBitmapSize(R.drawable.cop_west_left);
		// NORTH_EAST:
		bitmapArray[4][0] = scaleBitmapSize(R.drawable.cop_north_east);
		bitmapArray[4][1] = scaleBitmapSize(R.drawable.cop_north_east_right);
		bitmapArray[4][2] = scaleBitmapSize(R.drawable.cop_north_east);
		bitmapArray[4][3] = scaleBitmapSize(R.drawable.cop_north_east_left);
		// SOUTH_EAST:
		bitmapArray[5][0] = scaleBitmapSize(R.drawable.cop_south_east);
		bitmapArray[5][1] = scaleBitmapSize(R.drawable.cop_south_east_right);
		bitmapArray[5][2] = scaleBitmapSize(R.drawable.cop_south_east);
		bitmapArray[5][3] = scaleBitmapSize(R.drawable.cop_south_east_left);
		// NORTH_WEST:
		bitmapArray[6][0] = scaleBitmapSize(R.drawable.cop_north_west);
		bitmapArray[6][1] = scaleBitmapSize(R.drawable.cop_north_west_right);
		bitmapArray[6][2] = scaleBitmapSize(R.drawable.cop_north_west);
		bitmapArray[6][3] = scaleBitmapSize(R.drawable.cop_north_west_left);
		// SOUTH_WEST:
		bitmapArray[7][0] = scaleBitmapSize(R.drawable.cop_south_west);
		bitmapArray[7][1] = scaleBitmapSize(R.drawable.cop_south_west_right);
		bitmapArray[7][2] = scaleBitmapSize(R.drawable.cop_south_west);
		bitmapArray[7][3] = scaleBitmapSize(R.drawable.cop_south_west_left);

		images = bitmapArray;
	}

	@Override
	protected void main()
	{
		delay(200);

		currentImageIndex++;
		if (currentImageIndex > 3)
		{
			currentImageIndex = 0;
		}

		checkCollisionWithBackgroundBorder();
		checkCollisionWithCars();
		checkCollisionWithWalls();
		checkCollisionWithEnemy();
		changeDirection();
		updatePositionPoint();
	}

	public void updatePositionPoint()
	{
		positionPointX = positionPointX + speedHorizenal;
		positionPointY = positionPointY + speedVertical;
	}

	// Collision methods:
	// Border:
	private void checkCollisionWithBackgroundBorder()
	{
		switch (getCollisionWithBackgroungBorder())
		{
		case Border.TOP:
			speedVertical = speedVertical * -1;
			break;
		case Border.LEFT:
			speedHorizenal = speedHorizenal * -1;
			break;
		case Border.RIGHT:
			speedHorizenal = speedHorizenal * -1;
			break;
		case Border.BOTTOM:
			speedVertical = speedVertical * -1;
			break;
		case Border.TOP_LEFT:
			speedHorizenal = speedHorizenal * -1;
			speedVertical = speedVertical * -1;
			break;
		case Border.TOP_RIGHT:
			speedHorizenal = speedHorizenal * -1;
			speedVertical = speedVertical * -1;
			break;
		case Border.BOTTOM_LEFT:
			speedHorizenal = speedHorizenal * -1;
			speedVertical = speedVertical * -1;
			break;
		case Border.BOTTOM_RIGHT:
			speedHorizenal = speedHorizenal * -1;
			speedVertical = speedVertical * -1;
			break;
		}
	}

	private int getCollisionWithBackgroungBorder()
	{
		int offset = 15;
		// Sprite touching TOP side:
		if (positionPointY <= level.getCurrentBackgroundImage().getTOP()
				+ offset)
		{
			if (positionPointX <= level.getCurrentBackgroundImage().getLEFT()
					+ offset) // TOP side + LEFT side
			{
				return Border.TOP_LEFT;
			}
			if (positionPointX + bodyBounds.width() >= level
					.getCurrentBackgroundImage().getRIGHT() - offset) // TOP side + RIGHT side
			{
				return Border.TOP_RIGHT;
			}
			return Border.TOP; // default: only TOP side
		}

		// Sprite touching LEFT side:
		if (positionPointX <= level.getCurrentBackgroundImage().getLEFT()
				+ offset)
		{
			if (positionPointY <= level.getCurrentBackgroundImage().getTOP()
					+ offset) // LEFT side + TOP side
			{
				return Border.TOP_LEFT;
			}
			if (positionPointY + bodyBounds.height() >= level
					.getCurrentBackgroundImage().getBOTTOM() - offset) // LEFT side + BOTTOM side
			{
				return Border.BOTTOM_LEFT;
			}
			return Border.LEFT; // default: only LEFT side
		}

		// Sprite touching RIGHT side:
		if (positionPointX + bodyBounds.width() >= level
				.getCurrentBackgroundImage().getRIGHT() - offset)
		{
			if (positionPointY <= level.getCurrentBackgroundImage().getTOP()
					+ offset) // RIGHT side + TOP side
			{
				return Border.TOP_RIGHT;
			}
			if (positionPointY + bodyBounds.height() >= level
					.getCurrentBackgroundImage().getBOTTOM() - offset) // RIGHT side + BOTTOM side
			{
				return Border.BOTTOM_RIGHT;
			}
			return Border.RIGHT; // default: only RIGHT side 
		}

		// Sprite touching BOTTOM side:
		if (positionPointY + bodyBounds.height() >= level
				.getCurrentBackgroundImage().getBOTTOM() - offset)
		{
			if (positionPointX <= level.getCurrentBackgroundImage().getLEFT()
					+ offset) // LEFT side + BOTTOM side
			{
				return Border.BOTTOM_LEFT;
			}
			if (positionPointX + bodyBounds.width() >= level
					.getCurrentBackgroundImage().getRIGHT() - offset) // RIGHT side + BOTTOM side
			{
				return Border.BOTTOM_RIGHT;
			}
			return Border.BOTTOM; // default: only BOTTOM side
		}

		return Border.NO; // no collision with border
	}

	// Enemies:
	private void checkCollisionWithEnemy()
	{
		for (int j = 0; j < level.getEnemies().size(); j++)
		{
			if (this.getId() != level.getEnemies().get(j).getId()) // ignore collision with himself.
			{
				if (this.collidedWith(level.getEnemies().get(j)))
				{
					collidedWithEnemy();
				}
			}
		}
	}

	private void collidedWithEnemy()
	{
		this.setCurrendImageIndex(0);
		delay(500);

		switch (collisionSide)
		{
		case Collision.TOP:
			speedVertical *= -1;
			break;
		case Collision.LEFT:
			speedHorizenal *= -1;
			break;
		case Collision.RIGHT:
			speedHorizenal *= -1;
			break;
		case Collision.BOTTOM:
			speedVertical *= -1;
			break;
		default:
			break;
		}
	}

	// Cars:
	private void checkCollisionWithCars()
	{
		for (int j = 0; j < level.getCars().size(); j++)
		{
			if (this.collidedWith(level.getCars().get(j)))
			{
				collidedWithCars();
			}
		}
	}

	private void collidedWithCars()
	{
		switch (collisionSide)
		{
		case Collision.TOP:
			speedVertical *= -1;
			break;
		case Collision.LEFT:
			speedHorizenal *= -1;
			break;
		case Collision.RIGHT:
			speedHorizenal *= -1;
			break;
		case Collision.BOTTOM:
			speedVertical *= -1;
			break;
		default:
			break;
		}
	}

	// Walls:
	private void checkCollisionWithWalls()
	{
		for (int j = 0; j < level.getWalls().size(); j++)
		{
			if (this.collidedWith(level.getWalls().get(j)))
			{
				collidedWithWalls();
			}
		}
	}

	private void collidedWithWalls()
	{
		switch (collisionSide)
		{
		case Collision.TOP:
			speedVertical *= -1;
			break;
		case Collision.LEFT:
			speedHorizenal *= -1;
			break;
		case Collision.RIGHT:
			speedHorizenal *= -1;
			break;
		case Collision.BOTTOM:
			speedVertical *= -1;
			break;
		default:
			break;
		}
	}

	private void changeDirection()
	{
		if (speedHorizenal == 0 && speedVertical < 0)
		{
			direction = Direction.NORTH;
		}
		else if (speedHorizenal > 0 && speedVertical < 0)
		{
			direction = Direction.NORTH_EAST;
		}
		else if (speedHorizenal > 0 && speedVertical == 0)
		{
			direction = Direction.EAST;
		}
		else if (speedHorizenal > 0 && speedVertical > 0)
		{
			direction = Direction.SOUTH_EAST;
		}
		else if (speedHorizenal == 0 && speedVertical > 0)
		{
			direction = Direction.SOUTH;
		}
		else if (speedHorizenal < 0 && speedVertical > 0)
		{
			direction = Direction.SOUTH_WEST;
		}
		else if (speedHorizenal < 0 && speedVertical == 0)
		{
			direction = Direction.WEST;
		}
		else if (speedHorizenal < 0 && speedVertical < 0)
		{
			direction = Direction.NORTH_WEST;
		}
	}

}
