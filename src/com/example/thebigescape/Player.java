package com.example.thebigescape;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;

public class Player extends Object
{

	/** Variables: **/
	// Main Activity:
	MainActivity mainActivity = new MainActivity();

	// Context:
	private Context context;

	// Accelerometer Coordinates:
	private int accelerometerCoordinateX = MainActivity.accelerometerCoordinateX;
	private int accelerometerCoordinateY = MainActivity.accelerometerCoordinateY;

	// Characters:
	private int numberOfLifes = 0;
	private boolean gotKey = false;
	private boolean isGhost = false;

	// Level:
	private Level level;
	private int score = 0;
	private boolean gameOver = false;

	// Level controller:
	private LevelController levelController;

	// Sounds:
	private SoundManager soundManager;
	private int BOXING;
	private int BONUS;
	private int KEY;
	private int LEVEL_COMPLETE;
	private int GAME_OVER;
	private int steps[] = new int[3];

	/** Constructor: **/
	public Player(int positionPointX, int positionPointY, int speed,
			int numberOfLifes, MainActivity mainActivity, Context context,
			Canvas canvas, Level level)
	{
		super(positionPointX, positionPointY, canvas, context, mainActivity);

		this.context = context;
		this.speed = speed;
		this.numberOfLifes = numberOfLifes;
		this.level = level;
		this.mainActivity = mainActivity;

		initImages();
		initSounds();

		updateBodyBounds();

		levelController = new LevelController(context);
	}

	/** Methods: **/
	private void initImages()
	{
		Bitmap bitmapArray[][] = new Bitmap[8][4];

		// NORTH:
		bitmapArray[0][0] = scaleBitmapSize(R.drawable.player_north);
		bitmapArray[0][1] = scaleBitmapSize(R.drawable.player_north_right);
		bitmapArray[0][2] = scaleBitmapSize(R.drawable.player_north);
		bitmapArray[0][3] = scaleBitmapSize(R.drawable.player_north_left);
		// SOUTH:
		bitmapArray[1][0] = scaleBitmapSize(R.drawable.player_south);
		bitmapArray[1][1] = scaleBitmapSize(R.drawable.player_south_right);
		bitmapArray[1][2] = scaleBitmapSize(R.drawable.player_south);
		bitmapArray[1][3] = scaleBitmapSize(R.drawable.player_south_left);
		// EAST:
		bitmapArray[2][0] = scaleBitmapSize(R.drawable.player_east);
		bitmapArray[2][1] = scaleBitmapSize(R.drawable.player_east_right);
		bitmapArray[2][2] = scaleBitmapSize(R.drawable.player_east);
		bitmapArray[2][3] = scaleBitmapSize(R.drawable.player_east_left);
		// WEST:
		bitmapArray[3][0] = scaleBitmapSize(R.drawable.player_west);
		bitmapArray[3][1] = scaleBitmapSize(R.drawable.player_west_right);
		bitmapArray[3][2] = scaleBitmapSize(R.drawable.player_west);
		bitmapArray[3][3] = scaleBitmapSize(R.drawable.player_west_left);
		// NORTH_EAST:
		bitmapArray[4][0] = scaleBitmapSize(R.drawable.player_north_east);
		bitmapArray[4][1] = scaleBitmapSize(R.drawable.player_north_east_right);
		bitmapArray[4][2] = scaleBitmapSize(R.drawable.player_north_east);
		bitmapArray[4][3] = scaleBitmapSize(R.drawable.player_north_east_left);
		// SOUTH_EAST:
		bitmapArray[5][0] = scaleBitmapSize(R.drawable.player_south_east);
		bitmapArray[5][1] = scaleBitmapSize(R.drawable.player_south_east_right);
		bitmapArray[5][2] = scaleBitmapSize(R.drawable.player_south_east);
		bitmapArray[5][3] = scaleBitmapSize(R.drawable.player_south_east_left);
		// NORTH_WEST:
		bitmapArray[6][0] = scaleBitmapSize(R.drawable.player_north_west);
		bitmapArray[6][1] = scaleBitmapSize(R.drawable.player_north_west_right);
		bitmapArray[6][2] = scaleBitmapSize(R.drawable.player_north_west);
		bitmapArray[6][3] = scaleBitmapSize(R.drawable.player_north_west_left);
		// SOUTH_WEST:
		bitmapArray[7][0] = scaleBitmapSize(R.drawable.player_south_west);
		bitmapArray[7][1] = scaleBitmapSize(R.drawable.player_south_west_right);
		bitmapArray[7][2] = scaleBitmapSize(R.drawable.player_south_west);
		bitmapArray[7][3] = scaleBitmapSize(R.drawable.player_south_west_left);

		// load all images:
		images = bitmapArray;
	}

	private void initSounds()
	{
		soundManager = new SoundManager(context);

		BOXING = soundManager.load(R.raw.boxing);
		BONUS = soundManager.load(R.raw.bonus);
		KEY = soundManager.load(R.raw.key);
		LEVEL_COMPLETE = soundManager.load(R.raw.game_complete);
		GAME_OVER = soundManager.load(R.raw.game_over);
		steps[0] = soundManager.load(R.raw.step01);
		steps[1] = soundManager.load(R.raw.step02);
		steps[2] = soundManager.load(R.raw.step03);
	}

	// Accelerometer and Position methods:
	private void readAccelerometerCoordinates()
	{
		accelerometerCoordinateX = MainActivity.accelerometerCoordinateX;
		accelerometerCoordinateY = MainActivity.accelerometerCoordinateY;
	}

	private void updatePositionPoint()
	{
		// Change to a newer position:
		positionPointX = positionPointX + accelerometerCoordinateX * speed;
		positionPointY = positionPointY + accelerometerCoordinateY * speed;
	}

	// Direction method:
	public void changeDirection()
	{
		// set player direction
		if (accelerometerCoordinateX == 0 && accelerometerCoordinateY < 0)
		{
			direction = Direction.NORTH;
		}
		else if (accelerometerCoordinateX > 0 && accelerometerCoordinateY < 0)
		{
			direction = Direction.NORTH_EAST;
		}
		else if (accelerometerCoordinateX > 0 && accelerometerCoordinateY == 0)
		{
			direction = Direction.EAST;
		}
		else if (accelerometerCoordinateX > 0 && accelerometerCoordinateY > 0)
		{
			direction = Direction.SOUTH_EAST;
		}
		else if (accelerometerCoordinateX == 0 && accelerometerCoordinateY > 0)
		{
			direction = Direction.SOUTH;
		}
		else if (accelerometerCoordinateX < 0 && accelerometerCoordinateY > 0)
		{
			direction = Direction.SOUTH_WEST;
		}
		else if (accelerometerCoordinateX < 0 && accelerometerCoordinateY == 0)
		{
			direction = Direction.WEST;
		}
		else if (accelerometerCoordinateX < 0 && accelerometerCoordinateY < 0)
		{
			direction = Direction.NORTH_WEST;
		}

		// player is standing
		if (accelerometerCoordinateX == 0 && accelerometerCoordinateY == 0)
		{
			currentImageIndex = 0;
		}
		else
		{
			currentImageIndex++;
			if (currentImageIndex > 3)
			{
				currentImageIndex = 0;
			}

			Random randomNumber = new Random();
			soundManager.play(steps[randomNumber.nextInt(steps.length) + 0]);

			delay(100);

		}
	}

	// Collision Methods:
	private int getCollisionWithBorder()
	{
		int offset = 5;
		// Sprite touching TOP side:
		if (positionPointY <= mainActivity.screenTOP + offset)
		{
			if (positionPointX <= mainActivity.screenLEFT + offset) // TOP side + LEFT side
			{
				return Border.TOP_LEFT;
			}
			if (positionPointX + bodyBounds.width() >= mainActivity.screenRIGHT
					- offset) // TOP side + RIGHT side
			{
				return Border.TOP_RIGHT;
			}
			return Border.TOP; // default: only TOP side
		}

		// Sprite touching LEFT side:
		if (positionPointX <= mainActivity.screenLEFT + offset)
		{
			if (positionPointY <= mainActivity.screenTOP + offset) // LEFT side + TOP side
			{
				return Border.TOP_LEFT;
			}
			if (positionPointY + bodyBounds.height() >= mainActivity.screenBOTTOM
					- offset) // LEFT side + BOTTOM side
			{
				return Border.BOTTOM_LEFT;
			}
			return Border.LEFT; // default: only LEFT side
		}

		// Sprite touching RIGHT side:
		if (positionPointX + bodyBounds.width() >= mainActivity.screenRIGHT
				- offset)
		{
			if (positionPointY <= mainActivity.screenTOP + offset) // RIGHT side + TOP side
			{
				return Border.TOP_RIGHT;
			}
			if (positionPointY + bodyBounds.height() >= mainActivity.screenBOTTOM
					- offset) // RIGHT side + BOTTOM side
			{
				return Border.BOTTOM_RIGHT;
			}
			return Border.RIGHT; // default: only RIGHT side 
		}

		// Sprite touching BOTTOM side:
		if (positionPointY + bodyBounds.height() >= mainActivity.screenBOTTOM
				- offset)
		{
			if (positionPointX <= mainActivity.screenLEFT + offset) // LEFT side + BOTTOM side
			{
				return Border.BOTTOM_LEFT;
			}
			if (positionPointX + bodyBounds.width() >= mainActivity.screenRIGHT
					- offset) // RIGHT side + BOTTOM side
			{
				return Border.BOTTOM_RIGHT;
			}
			return Border.BOTTOM; // default: only BOTTOM side
		}

		return Border.NO; // no collision with border
	}

	private void checkCollisionWithBorder()
	{
		switch (getCollisionWithBorder())
		{
		case Border.TOP:
			if (accelerometerCoordinateY < 0) // phone is tilted up
				accelerometerCoordinateY = 0;
			break;

		case Border.LEFT:
			if (accelerometerCoordinateX < 0) // phone is tilted left
				accelerometerCoordinateX = 0;
			break;

		case Border.RIGHT:
			if (accelerometerCoordinateX > 0) // phone is tilted right
				accelerometerCoordinateX = 0;
			break;

		case Border.BOTTOM:
			if (accelerometerCoordinateY > 0) // phone is tilted down
				accelerometerCoordinateY = 0;
			break;

		case Border.TOP_LEFT:
			if (accelerometerCoordinateX < 0) // phone is tilted left is enough
				accelerometerCoordinateX = 0;
			if (accelerometerCoordinateY < 0) // if phone will tilt up as well
				accelerometerCoordinateY = 0;
			break;

		case Border.TOP_RIGHT:
			if (accelerometerCoordinateY < 0) // tilting phone up is enough
				accelerometerCoordinateY = 0;
			if (accelerometerCoordinateX > 0) // check if tilted right as well
				accelerometerCoordinateX = 0;
			break;

		case Border.BOTTOM_LEFT:
			if (accelerometerCoordinateX < 0) // phone is tilted left is enough
				accelerometerCoordinateX = 0;
			if (accelerometerCoordinateY > 0) // if phone will tilt down as well
				accelerometerCoordinateY = 0;
			break;

		case Border.BOTTOM_RIGHT:
			if (accelerometerCoordinateX > 0) // right
				accelerometerCoordinateX = 0;
			if (accelerometerCoordinateY > 0) // down
				accelerometerCoordinateY = 0;
			break;

		}
	}

	// Keys:
	private void checkCollisionWithKeys()
	{
		for (int j = 0; j < level.getKeys().size(); j++)
		{
			if (collidedWith(level.getKeys().get(j)))
			{
				if (!isGhost)
				{
					collidedWithKeys(j);
				}
			}
		}
	}

	private void collidedWithKeys(int index)
	{
		level.getKeys().remove(index);
		soundManager.play(KEY);
		if (level.getKeys().isEmpty()) // if no more keys
		{
			gotKey = true;
		}
	}

	// End Point:
	private void checkCollisionWithEndPoint()
	{
		if (collidedWith(level.getEndPoint().get(0)))
		{
			if (!this.isGhost)
			{
				collidedWithEndPoint();
			}
		}
	}

	private void collidedWithEndPoint()
	{
		if (gotKey)
		{
			currentImageIndex = 0; // player is standing, not walking.

			gameOver = true;
			level.pause();

			int unlockedLevels = levelController.readFromFile();
			if (unlockedLevels < 5 && level.getLevelId() == unlockedLevels)
			{
				unlockedLevels++;
				levelController.writeToFile(unlockedLevels);
			}
			soundManager.play(LEVEL_COMPLETE);
			showScreen("level complete");
			return;
		}
	}

	// Enemies:
	private void checkCollisionWithEnemies()
	{
		for (Enemy i : level.getEnemies())
		{
			if (collidedWith(i))
			{
				if (!isGhost)
				{
					collidedWithEnemy();
				}
			}
		}
	}

	private void collidedWithEnemy()
	{

		currentImageIndex = 0; // player is standing, not walking.
		score -= 100;
		numberOfLifes--;
		soundManager.play(BOXING);

		if (numberOfLifes <= 0)
		{
			gameOver = true;
			level.pause();
			soundManager.play(GAME_OVER);
			showScreen("game over");
			return;
		}

		isGhost = true;

		switch (this.direction)
		{
		case Direction.NORTH:
			positionPointY += 10;
			break;
		case Direction.NORTH_EAST:
			positionPointX -= 10;
			positionPointY += 10;
			break;
		case Direction.EAST:
			positionPointX -= 10;
			break;
		case Direction.SOUTH_EAST:
			positionPointX -= 10;
			positionPointY -= 10;
			break;
		case Direction.SOUTH:
			positionPointY -= 10;
			break;
		case Direction.SOUTH_WEST:
			positionPointX += 10;
			positionPointY -= 10;
			break;
		case Direction.WEST:
			positionPointX += 10;
			break;
		case Direction.NORTH_WEST:
			positionPointX += 10;
			positionPointY += 10;
			break;
		default:
			break;
		}

		// ghost for a while:
		for (int i = 1; i < 15; i++)
		{
			blink(100);
		}
		isGhost = false;
	}

	// Bonus:
	private void checkCollisionWithBonus()
	{
		for (int j = 0; j < level.getBonus().size(); j++)
		{
			if (collidedWith(level.getBonus().get(j)))
			{
				if (!isGhost)
				{
					collidedWithBonus(j);
				}
			}
		}
	}

	private void collidedWithBonus(int index)
	{
		soundManager.play(BONUS);
		level.getBonus().remove(index);
		score += 100;
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
			if (accelerometerCoordinateY < 0) // phone is tilted up
				accelerometerCoordinateY = 0;
			break;
		case Collision.LEFT:
			if (accelerometerCoordinateX < 0) // phone is tilted left
				accelerometerCoordinateX = 0;
			break;
		case Collision.RIGHT:
			if (accelerometerCoordinateX > 0) // phone is tilted right
				accelerometerCoordinateX = 0;
			break;
		case Collision.BOTTOM:
			if (accelerometerCoordinateY > 0) // phone is tilted down
				accelerometerCoordinateY = 0;
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
			if (accelerometerCoordinateY < 0) // phone is tilted up
				accelerometerCoordinateY = 0;
			break;
		case Collision.LEFT:
			if (accelerometerCoordinateX < 0) // phone is tilted left
				accelerometerCoordinateX = 0;
			break;
		case Collision.RIGHT:
			if (accelerometerCoordinateX > 0) // phone is tilted right
				accelerometerCoordinateX = 0;
			break;
		case Collision.BOTTOM:
			if (accelerometerCoordinateY > 0) // phone is tilted down
				accelerometerCoordinateY = 0;
			break;
		default:
			break;
		}
	}

	protected void main()
	{
		if (!gameOver)
		{
			delay(10);

			readAccelerometerCoordinates();
			changeDirection();
			checkCollisionWithBorder();
			checkCollisionWithEnemies();
			checkCollisionWithKeys();
			checkCollisionWithEndPoint();
			checkCollisionWithBonus();
			checkCollisionWithWalls();
			checkCollisionWithCars();
			updatePositionPoint();
			moveAllOtherAccordingToPlayer();
			updateBodyBounds();
		}
	}

	public void showScreen(final String screenName)
	{

		mainActivity.runOnUiThread(new Runnable()
		{
			public void run()
			{
				final Dialog dialog;
				dialog = new Dialog(context);
				ImageView view = new ImageView(context);

				if (screenName.equals("game over"))
				{
					view.setImageResource(R.drawable.game_over);
				}
				if (screenName.equals("level complete"))
				{
					view.setImageResource(R.drawable.level_complete);
				}

				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(view);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.show();

				final Timer timer = new Timer();
				timer.schedule(new TimerTask()
				{
					public void run()
					{
						mainActivity.gotoMenuActivity();
						dialog.dismiss();
						timer.cancel();
					}
				}, 1000); // disappear after 1 second

			}
		});
	}

	public int getLife()
	{
		return numberOfLifes;
	}

	public int getScore()
	{
		return score;
	}

	public void setGameOver()
	{
		gameOver = true;
		showScreen("game over");
	}

	private void moveAllOtherAccordingToPlayer()
	{

		level.getCurrentBackgroundImage().setPositionPointX(
				level.getCurrentBackgroundImage().getPositionPointX()
						- this.accelerometerCoordinateX * this.speed);
		level.getCurrentBackgroundImage().setPositionPointY(
				level.getCurrentBackgroundImage().getPositionPointY()
						- this.accelerometerCoordinateY * this.speed);

		for (Enemy i : level.getEnemies())
		{
			i.setPositionPointX(i.getPositionPointX()
					- this.accelerometerCoordinateX * this.speed);
			i.setPositionPointY(i.getPositionPointY()
					- this.accelerometerCoordinateY * this.speed);
		}

		for (Key i : level.getKeys())
		{
			i.setPositionPointX(i.getPositionPointX()
					- this.accelerometerCoordinateX * this.speed);
			i.setPositionPointY(i.getPositionPointY()
					- this.accelerometerCoordinateY * this.speed);
		}

		for (EndPoint i : level.getEndPoint())
		{
			i.setPositionPointX(i.getPositionPointX()
					- this.accelerometerCoordinateX * this.speed);
			i.setPositionPointY(i.getPositionPointY()
					- this.accelerometerCoordinateY * this.speed);
		}

		for (Car i : level.getCars())
		{
			i.setPositionPointX(i.getPositionPointX()
					- this.accelerometerCoordinateX * this.speed);
			i.setPositionPointY(i.getPositionPointY()
					- this.accelerometerCoordinateY * this.speed);
		}

		for (Bonus i : level.getBonus())
		{
			i.setPositionPointX(i.getPositionPointX()
					- this.accelerometerCoordinateX * this.speed);
			i.setPositionPointY(i.getPositionPointY()
					- this.accelerometerCoordinateY * this.speed);
		}

		for (Wall i : level.getWalls())
		{
			i.bodyBounds.left -= this.accelerometerCoordinateX * this.speed;
			i.bodyBounds.top -= this.accelerometerCoordinateY * this.speed;
			i.bodyBounds.right -= this.accelerometerCoordinateX * this.speed;
			i.bodyBounds.bottom -= this.accelerometerCoordinateY * this.speed;
		}
	}

}
