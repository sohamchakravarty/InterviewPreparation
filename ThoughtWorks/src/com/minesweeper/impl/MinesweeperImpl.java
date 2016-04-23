package com.minesweeper.impl;

import com.minesweeper.utils.Minesweeper;
import com.minesweeper.utils.LocationData;
import com.minesweeper.utils.UserInput;

public class MinesweeperImpl implements Minesweeper {
	
	private LocationData[][] minefield;
	private int minefieldDimension;
	private int countOfCorrectInputs;
	private boolean steppedOnMine;
	
	public MinesweeperImpl(char[][] input) {
		createMinefield(input);
		countOfCorrectInputs = 0;
	}

	@Override
	public void reveal(int rowNumber, int columnNumber) {
		if(!validateRowColumn(rowNumber, columnNumber))
			throw new IllegalArgumentException("Invalid row and column entered");
		
		if(!minefield[rowNumber][columnNumber].isOpen()) {
			minefield[rowNumber][columnNumber].setUserData(UserInput.OPEN);
			countOfCorrectInputs++;
		}
		
		steppedOnMine = hasSteppedOnMine(rowNumber, columnNumber);
	}

	@Override
	public void flag(int rowNumber, int columnNumber) {
		if(!validateRowColumn(rowNumber, columnNumber))
			throw new IllegalArgumentException("Invalid row and column entered");
		
		minefield[rowNumber][columnNumber].setUserData(UserInput.FLAG);
		if(minefield[rowNumber][columnNumber].isMine()) {
			countOfCorrectInputs++;
		}
	}

	@Override
	public void clearAround(int rowNumber, int columnNumber) {
		reveal(rowNumber, columnNumber);
		
		if(canGetLeftSquare(rowNumber, columnNumber) && !minefield[rowNumber][columnNumber-1].isFlagged()) //ignore if flagged
			reveal(rowNumber, columnNumber-1);
		
		if(canGetRightSquare(rowNumber, columnNumber) && !minefield[rowNumber][columnNumber+1].isFlagged())
			reveal(rowNumber, columnNumber+1);
		
		if(canGetTopSquare(rowNumber, columnNumber) && !minefield[rowNumber-1][columnNumber].isFlagged())
			reveal(rowNumber-1, columnNumber);
		
		if(canGetBottomSquare(rowNumber, columnNumber) && !minefield[rowNumber+1][columnNumber].isFlagged())
			reveal(rowNumber+1, columnNumber);
			
	}
	
	@Override
	public LocationData[][] getMinefield() {
		return this.minefield;
	}
	
	@Override
	public boolean isGameOver() {
		if(steppedOnMine) {
			System.out.println("You stepped on mine. Game Over!");
			return true;
		}
		else if(countOfCorrectInputs == maxCorrectInputs()) {
			System.out.println("Congratulations! You have won!");
			return true;
		}
		return false;
	}

	//creates initial minefield
	private void createMinefield(char[][] input) {
		this.minefieldDimension = input.length;
		this.minefield = new LocationData[minefieldDimension][minefieldDimension];
		
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				minefield[i][j] = new LocationData();
			}
		}
		
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				if(input[i][j] == 'm') {
					updateMinefield(i,j);
					minefield[i][j].setData(-1);
				}
			}
		}
		
		System.out.println();
	}

	private void updateMinefield(int i, int j) {
		if(canGetLeftSquare(i, j) && !minefield[i][j-1].isMine())
			minefield[i][j-1].setData(minefield[i][j-1].getData() + 1);;

		if(canGetRightSquare(i, j) && !minefield[i][j+1].isMine())
			minefield[i][j+1].setData(minefield[i][j+1].getData() + 1);;
		
		if(canGetTopSquare(i, j) && !minefield[i-1][j].isMine())
			minefield[i-1][j].setData(minefield[i-1][j].getData() + 1);
		
		if(canGetBottomSquare(i, j) && !minefield[i+1][j].isMine())
			minefield[i+1][j].setData(minefield[i+1][j].getData() + 1);;
	}
	
	private boolean validateRowColumn(int rowNumber, int columnNumber) {
		return ((rowNumber>=0 && rowNumber<minefieldDimension) && 
				(columnNumber>=0 && columnNumber<minefieldDimension));
	}

	private boolean canGetLeftSquare(int rowNumber, int columnNumber) {
		return (columnNumber!=0);
	}
	
	private boolean canGetRightSquare(int rowNumber, int columnNumber) {
		return (columnNumber!=minefieldDimension-1);
	}
	
	private boolean canGetTopSquare(int rowNumber, int columnNumber) {
		return (rowNumber!=0);
	}
	
	private boolean canGetBottomSquare(int rowNumber, int columnNumber) {
		return (rowNumber!=minefieldDimension-1);
	}

	private boolean hasSteppedOnMine(int i, int j) {
		return (steppedOnMine || (minefield[i][j].isMine() && !minefield[i][j].isFlagged()));
	}

	
	//returns maximum possible correct inputs that can be entered by user
	private int maxCorrectInputs() {
		return minefieldDimension*minefieldDimension;
	}

}
