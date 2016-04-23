package com.minesweeper.impl;

import com.minesweeper.utils.LocationData;
import com.minesweeper.utils.Printer;

public class ConsolePrinter implements Printer {

	@Override
	public void printMinefield(LocationData[][] minefield) {
		for(int i=0; i<minefield.length; i++) {
			for(int j=0; j<minefield.length; j++) {
				if(!minefield[i][j].isOpen() && !minefield[i][j].isFlagged())
					System.out.print("x ");
				else {
					if(minefield[i][j].isFlagged())
						System.out.print("f ");
					else if(minefield[i][j].isMine())
						System.out.print("m ");
					else
						System.out.print(minefield[i][j].getData() + " ");
				}
			}
			System.out.println();
		}
		
		System.out.println("---------------------------");
	}

}
