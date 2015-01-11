package com.example.thebigescape;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;

public class Level
{

	/** Variables: **/
	// Objects:
	private ArrayList<Player> player = new ArrayList<Player>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bonus> bonus = new ArrayList<Bonus>();
	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Key> keys = new ArrayList<Key>();
	private ArrayList<EndPoint> endPoint = new ArrayList<EndPoint>();
	private ArrayList<BackgroundImage> backgroundImages = new ArrayList<BackgroundImage>();

	// Context:
	private Context context;

	// View:
	private ImageView imageView;

	// Canvas:
	private Canvas canvas;

	// Main Activity:
	private MainActivity mainActivity;

	// Level:
	private int levelId = 0;
	private Bitmap hearts;

	// Handler:
	private Handler handler = new Handler();

	// CountDownTimer:
	private CountDownTimer countDownTimer;
	private long totalTimeCountInMilliseconds;

	/** Constructor: **/
	public Level(Context context, ImageView imageView, Canvas canvas,
			MainActivity mainActivity, int levelId)
	{
		this.context = context;
		this.imageView = imageView;
		this.canvas = canvas;
		this.mainActivity = mainActivity;
		this.levelId = levelId;

		hearts = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.heart);

	}

	/** Methods: **/
	private void loadLevel(int levelId)
	{
		int x = 0;
		int y = 0;

		switch (levelId)
		{
		case 1:
			x = 160;
			y = 10;
			backgroundImages.add(new BackgroundImage(0 - x, 0 - y, canvas,
					decodePathToBitmap(R.drawable.level01_bg)));
			walls.add(new Wall(0 - x, 0 - y, 300 - x, 760 - y, context, canvas,
					mainActivity));
			walls.add(new Wall(700 - x, 0 - y, 1215 - x, 550 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1600 - x, 0 - y, 1920 - x, 755 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(0 - x, 890 - y, 410 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(410 - x, 1000 - y, 1710 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1710 - x, 880 - y, 1920 - x, 1080 - y, context,
					canvas, mainActivity));
			bonus.add(new Bonus(40 - x, 820 - y, "dollar", context, canvas,
					mainActivity));
			bonus.add(new Bonus(1900 - x, 800 - y, "money_bag", context,
					canvas, mainActivity));
			endPoint.add(new EndPoint(1000 - x, 1000 - y, context, canvas,
					mainActivity));
			cars.add(new Car(450 - x, 700 - y, context, canvas, mainActivity,
					Direction.NORTH));
			cars.add(new Car(650 - x, 700 - y, context, canvas, mainActivity,
					Direction.SOUTH));
			cars.add(new Car(850 - x, 700 - y, context, canvas, mainActivity,
					Direction.NORTH));
			cars.add(new Car(1050 - x, 700 - y, context, canvas, mainActivity,
					Direction.SOUTH));
			cars.add(new Car(1250 - x, 700 - y, context, canvas, mainActivity,
					Direction.NORTH));
			cars.add(new Car(1450 - x, 700 - y, context, canvas, mainActivity,
					Direction.SOUTH));
			enemies.add(new Enemy(200 - x, 720 - y, 0, 6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(550 - x, 0 - y, -6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(350 - x, 100 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(450 - x, 200 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(550 - x, 300 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(650 - x, 400 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1450 - x, 200 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1550 - x, 300 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1650 - x, 400 - y, 6, 0, context, canvas,
					mainActivity, this));
			keys.add(new Key(1500 - x, 40 - y, context, canvas, mainActivity));
			keys.add(new Key(650 - x, 20 - y, context, canvas, mainActivity));
			player.add(new Player(x, y, 2, 3, mainActivity, context,
					this.canvas, this));
			mainActivity.mediaPlayer = MediaPlayer.create(context,
					R.raw.city_ambiance);
			totalTimeCountInMilliseconds = 150000;
			setCountDownTimer();

			break;

		case 2:
			x = 920;
			y = 235;
			backgroundImages.add(new BackgroundImage(0 - x, 0 - y, canvas,
					decodePathToBitmap(R.drawable.level02_bg)));
			walls.add(new Wall(0 - x, 0 - y, 1640 - x, 272 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(0 - x, 0 - y, 210 - x, 780 - y, context, canvas,
					mainActivity));
			walls.add(new Wall(0 - x, 780 - y, 1435 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1640 - x, 0 - y, 1800 - x, 150 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1800 - x, 0 - y, 1920 - x, 272 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(485 - x, 510 - y, 1020 - x, 520 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(0 - x, 270 - y, 20 - x, 780 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(0 - x, 780 - y, 1440 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1440 - x, 510 - y, 1920 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1020 - x, 380 - y, 1640 - x, 675 - y, context,
					canvas, mainActivity));
			bonus.add(new Bonus(880 - x, 540 - y, "dollar", context, canvas,
					mainActivity));
			endPoint.add(new EndPoint(1410 - x, 750 - y, context, canvas,
					mainActivity));
			cars.add(new Car(1810 - x, 280 - y, context, canvas, mainActivity,
					Direction.NORTH));
			enemies.add(new Enemy(1200 - x, 300 - y, 0, 2, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1300 - x, 300 - y, 0, -2, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1400 - x, 300 - y, 0, 2, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(540 - x, 680 - y, -6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(580 - x, 680 - y, 6, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(600 - x, 680 - y, -6, 0, context, canvas,
					mainActivity, this));
			keys.add(new Key(580 - x, 680 - y, context, canvas, mainActivity));
			player.add(new Player(x, y, 2, 3, mainActivity, context,
					this.canvas, this));
			mainActivity.mediaPlayer = MediaPlayer.create(context,
					R.raw.city_ambiance);
			totalTimeCountInMilliseconds = 120000;
			setCountDownTimer();

			break;
		case 3:
			x = 35;
			y = 135;
			backgroundImages.add(new BackgroundImage(0 - x, 0 - y, canvas,
					decodePathToBitmap(R.drawable.level03_bg)));
			walls.add(new Wall(0 - x, 0 - y, 120 - x, 220 - y, context, canvas,
					mainActivity));
			walls.add(new Wall(120 - x, 0 - y, 410 - x, 720 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(410 - x, 690 - y, 640 - x, 725 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(0 - x, 910 - y, 640 - x, 960 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(715 - x, 0 - y, 990 - x, 345 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(990 - x, 0 - y, 1460 - x, 120 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1760 - x, 0 - y, 1920 - x, 850 - y, context,
					canvas, mainActivity));
			bonus.add(new Bonus(880 - x, 540 - y, "dollar", context, canvas,
					mainActivity));
			bonus.add(new Bonus(30 - x, 1000 - y, "money_bag", context, canvas,
					mainActivity));
			endPoint.add(new EndPoint(1600 - x, 20 - y, context, canvas,
					mainActivity));
			cars.add(new Car(800 - x, 350 - y, context, canvas, mainActivity,
					Direction.EAST));
			enemies.add(new Enemy(200 - x, 720 - y, 0, 6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(440 - x, 200 - y, 2, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(500 - x, 800 - y, 0, 6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(700 - x, 800 - y, 0, 6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(900 - x, 800 - y, 0, 6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1100 - x, 800 - y, 0, 6, context, canvas,
					mainActivity, this));
			keys.add(new Key(1800 - x, 1000 - y, context, canvas, mainActivity));
			keys.add(new Key(450 - x, 30 - y, context, canvas, mainActivity));
			player.add(new Player(x, y, 2, 2, mainActivity, context,
					this.canvas, this));
			mainActivity.mediaPlayer = MediaPlayer
					.create(context, R.raw.nature);
			totalTimeCountInMilliseconds = 150000;
			setCountDownTimer();

			break;

		case 4:
			x = 900;
			y = 40;
			backgroundImages.add(new BackgroundImage(0 - x, 0 - y, canvas,
					decodePathToBitmap(R.drawable.level04_bg)));
			walls.add(new Wall(0 - x, 0 - y, 90 - x, 1080 - y, context, canvas,
					mainActivity));
			walls.add(new Wall(590 - x, 0 - y, 1560 - x, 400 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1380 - x, 400 - y, 1560 - x, 985 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1560 - x, 0 - y, 1655 - x, 220 - y, context,
					canvas, mainActivity));
			bonus.add(new Bonus(880 - x, 540 - y, "dollar", context, canvas,
					mainActivity));
			bonus.add(new Bonus(1100 - x, 540 - y, "money_bag", context,
					canvas, mainActivity));
			bonus.add(new Bonus(1320 - x, 540 - y, "diamond", context, canvas,
					mainActivity));
			endPoint.add(new EndPoint(140 - x, 40 - y, context, canvas,
					mainActivity));
			cars.add(new Car(820 - x, 720 - y, context, canvas, mainActivity,
					Direction.EAST));
			cars.add(new Car(1000 - x, 900 - y, context, canvas, mainActivity,
					Direction.WEST));
			enemies.add(new Enemy(1800 - x, 1000 - y, 6, 6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1700 - x, 1000 - y, -6, -6, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(880 - x, 540 - y, 0, 10, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1100 - x, 540 - y, 0, 10, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(880 - x, 1000 - y, 0, 10, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(120 - x, 250 - y, 5, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(520 - x, 250 - y, -5, 0, context, canvas,
					mainActivity, this));
			keys.add(new Key(880 - x, 500 - y, context, canvas, mainActivity));
			player.add(new Player(x, y, 2, 2, mainActivity, context,
					this.canvas, this));
			mainActivity.mediaPlayer = MediaPlayer
					.create(context, R.raw.nature);
			totalTimeCountInMilliseconds = 120000;
			setCountDownTimer();

			break;

		case 5:
			x = 20;
			y = 20;
			backgroundImages.add(new BackgroundImage(0 - x, 0 - y, canvas,
					decodePathToBitmap(R.drawable.level05_bg)));
			walls.add(new Wall(0 - x, 210 - y, 380 - x, 680 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(130 - x, 100 - y, 600 - x, 580 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(380 - x, 680 - y, 1510 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(820 - x, 460 - y, 930 - x, 570 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(940 - x, 450 - y, 1550 - x, 690 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1160 - x, 350 - y, 1420 - x, 450 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1480 - x, 100 - y, 1920 - x, 350 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1700 - x, 450 - y, 1920 - x, 1080 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(1810 - x, 350 - y, 1420 - x, 450 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(810 - x, 0 - y, 1280 - x, 120 - y, context,
					canvas, mainActivity));
			walls.add(new Wall(940 - x, 120 - y, 1050 - x, 230 - y, context,
					canvas, mainActivity));

			bonus.add(new Bonus(430 - x, 640 - y, "dollar", context, canvas,
					mainActivity));
			endPoint.add(new EndPoint(1600 - x, 1000 - y, context, canvas,
					mainActivity));
			enemies.add(new Enemy(640 - x, 500 - y, 3, 0, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(870 - x, 160 - y, 0, 3, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1000 - x, 260 - y, 0, 3, context, canvas,
					mainActivity, this));
			enemies.add(new Enemy(1200 - x, 160 - y, 3, 0, context, canvas,
					mainActivity, this));
			keys.add(new Key(1850 - x, 30 - y, context, canvas, mainActivity));
			keys.add(new Key(860 - x, 620 - y, context, canvas, mainActivity));
			player.add(new Player(x, y, 2, 2, mainActivity, context,
					this.canvas, this));
			mainActivity.mediaPlayer = MediaPlayer
					.create(context, R.raw.nature);
			totalTimeCountInMilliseconds = 120000;
			setCountDownTimer();

			break;
		}

		mainActivity.mediaPlayer.setLooping(true);
	}

	public void start()
	{
		System.out.println("level Started");

		loadLevel(levelId);

		player.get(0).start();

		for (Enemy i : enemies)
		{
			i.start();
		}

		for (Key i : keys)
		{
			i.start();
		}

		for (EndPoint i : endPoint)
		{
			i.start();
		}

		for (Car i : cars)
		{
			i.start();
		}

		for (Bonus i : bonus)
		{
			i.start();
		}

		for (Wall i : walls)
		{
			i.start();
		}
	}

	public void restart()
	{
		System.out.println("level Restarted");

		destroy();
		start();
	}

	public void resume()
	{
		System.out.println("level Resumed");

		// Start Drawing:
		handler.postDelayed(stepper, 50);

		if (countDownTimer != null)
		{
			setCountDownTimer();
			countDownTimer.start();
		}

		// Resume Sprite:
		player.get(0).setIsRunning(true);

		for (Enemy i : enemies)
		{
			i.setIsRunning(true);
		}

		for (Key i : keys)
		{
			i.setIsRunning(true);
		}

		for (EndPoint i : endPoint)
		{
			i.setIsRunning(true);
		}

		for (Car i : cars)
		{
			i.setIsRunning(true);
		}

		for (Bonus i : bonus)
		{
			i.setIsRunning(true);
		}

		for (Wall i : walls)
		{
			i.setIsRunning(true);
		}
	}

	public void pause()
	{
		System.out.println("level Paused");

		// Stop drawing:
		handler.removeCallbacks(stepper);

		if (countDownTimer != null)
		{
			countDownTimer.cancel(); // pause timer
		}

		// Pause Sprite:
		player.get(0).setIsRunning(false);

		for (Enemy i : enemies)
		{
			i.setIsRunning(false);
		}

		for (Key i : keys)
		{
			i.setIsRunning(true);
		}

		for (EndPoint i : endPoint)
		{
			i.setIsRunning(true);
		}

		for (Car i : cars)
		{
			i.setIsRunning(true);
		}

		for (Bonus i : bonus)
		{
			i.setIsRunning(true);
		}

		for (Wall i : walls)
		{
			i.setIsRunning(true);
		}

	}

	public void destroy()
	{
		System.out.println("level Destroyed");

		// Stop all Sprite threads:
		player.get(0).setIsAlive(false);

		if (countDownTimer != null)
		{
			countDownTimer.cancel();
			countDownTimer = null;
		}

		for (Enemy i : enemies)
		{
			i.setIsAlive(false);
		}

		for (Key i : keys)
		{
			i.setIsAlive(false);
		}

		for (EndPoint i : endPoint)
		{
			i.setIsAlive(false);
		}

		for (Car i : cars)
		{
			i.setIsAlive(false);
		}

		for (Bonus i : bonus)
		{
			i.setIsAlive(false);
		}

		for (Wall i : walls)
		{
			i.setIsAlive(false);
		}

		// Clear all Sprite lists:
		clearAll();
	}

	private void clearAll()
	{
		player.clear();
		enemies.clear();
		keys.clear();
		endPoint.clear();
		cars.clear();
		bonus.clear();
		walls.clear();
		backgroundImages.clear();
	}

	private void draw()
	{
		getCurrentBackgroundImage().drawImage();
		player.get(0).drawImage();

		for (Enemy i : enemies)
		{
			i.drawImage();
		}

		for (Key i : keys)
		{
			i.drawImage();
		}

		for (EndPoint i : endPoint)
		{
			i.drawImage();
		}

		for (Car i : cars)
		{
			i.drawImage();
		}

		for (Bonus i : bonus)
		{
			i.drawImage();
		}

		for (int i = 1; i <= player.get(0).getLife(); i++)
		{
			canvas.drawBitmap(hearts, i * 60, 20, null);
		}

		Paint score = new Paint();
		score.setColor(Color.GREEN);
		score.setTextAlign(Paint.Align.LEFT);
		score.setTextSize(30);
		canvas.drawText("SCORE: " + getPlayer().get(0).getScore() + "$",
				mainActivity.screenWIDTH - 200, 50, score);

		if (countDownTimer != null)
		{
			Paint paintTimer = new Paint();
			paintTimer.setColor(Color.RED);
			paintTimer.setTextAlign(Paint.Align.LEFT);
			paintTimer.setTextSize(35);

			long seconds = totalTimeCountInMilliseconds / 1000;

			// Show counting:
			canvas.drawText(
					String.format("%02d", seconds / 60) + ":"
							+ String.format("%02d", seconds % 60),
					mainActivity.screenWIDTH - 200, 100, paintTimer);
		}
	}

	private Runnable stepper = new Runnable()
	{
		@Override
		public void run()
		{
			// Draw & Refresh imageView:
			draw();
			imageView.postInvalidate();

			// Loop "run" again:
			handler.postDelayed(this, 50);
		}
	};

	protected Bitmap decodePathToBitmap(int resourcePath)
	{
		Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				context.getResources(), resourcePath), 1920, 1080, false);
		bitmap.setDensity(240);
		System.out.println(bitmap.getHeight() + " " + bitmap.getWidth() + " "
				+ bitmap.getDensity());
		return bitmap;
	}

	private void setCountDownTimer()
	{
		countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 1000)
		{
			@Override
			public void onTick(long leftTimeInMilliseconds)
			{
				totalTimeCountInMilliseconds = leftTimeInMilliseconds;
			}

			@Override
			public void onFinish()
			{
				Paint paintTimer = new Paint();
				paintTimer.setColor(Color.RED);
				paintTimer.setTextAlign(Paint.Align.LEFT);
				paintTimer.setTextSize(30);

				canvas.drawText("Times Up!!", mainActivity.screenWIDTH - 400,
						50, paintTimer);

				getPlayer().get(0).setGameOver();
			}
		};
	}

	public long getTotalTimeCountInMilliseconds()
	{
		return totalTimeCountInMilliseconds;
	}

	/** Getters: **/
	public int getLevelId()
	{
		return levelId;
	}

	public ArrayList<Player> getPlayer()
	{
		return player;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}

	public ArrayList<Key> getKeys()
	{
		return keys;
	}

	public ArrayList<EndPoint> getEndPoint()
	{
		return endPoint;
	}

	public ArrayList<Car> getCars()
	{
		return cars;
	}

	public ArrayList<Bonus> getBonus()
	{
		return bonus;
	}

	public ArrayList<Wall> getWalls()
	{
		return walls;
	}

	public ArrayList<BackgroundImage> getBackgroundImagess()
	{
		return backgroundImages;
	}

	public BackgroundImage getCurrentBackgroundImage()
	{
		return backgroundImages.get(0);
	}

}
