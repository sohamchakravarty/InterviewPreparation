package com.minesweeper.utils;

public interface Minesweeper {
	void reveal(int rowNumber, int columnNumber);
	void flag(int rowNumber, int columnNumber);
	void clearAround(int rowNumber, int columnNumber);
	LocationData[][] getMinefield();
	boolean isGameOver();
}
