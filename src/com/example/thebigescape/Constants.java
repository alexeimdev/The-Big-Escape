package com.example.thebigescape;

class Border
{
	static final int NO = 0;
	static final int TOP = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int BOTTOM = 4;
	static final int TOP_LEFT = 12; // (12 = 1 + 2 -> TOP + LEFT)
	static final int TOP_RIGHT = 13; // (13 = 1 + 3 -> TOP + RIGHT)
	static final int BOTTOM_LEFT = 24; // (24 = 2 + 4 -> LEFT + BOTTOM)
	static final int BOTTOM_RIGHT = 34; // (34 = 3 + 4 -> RIGHT + BOTTOM)
}

class Direction
{
	static final int NORTH = 0;
	static final int SOUTH = 1;
	static final int EAST = 2;
	static final int WEST = 3;
	static final int NORTH_EAST = 4;
	static final int SOUTH_EAST = 5;
	static final int NORTH_WEST = 6;
	static final int SOUTH_WEST = 7;

}

class Collision
{
	static final int NO = 0;
	static final int TOP = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int BOTTOM = 4;
	static final int TOP_LEFT = 12; // (12 = 1 + 2 -> TOP + LEFT)
	static final int TOP_RIGHT = 13; // (13 = 1 + 3 -> TOP + RIGHT)
	static final int BOTTOM_LEFT = 24; // (24 = 2 + 4 -> LEFT + BOTTOM)
	static final int BOTTOM_RIGHT = 34; // (34 = 3 + 4 -> RIGHT + BOTTOM)
}