package com.minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.minesweeper.impl.ConsolePrinter;
import com.minesweeper.impl.MinesweeperImpl;
import com.minesweeper.utils.Minesweeper;
import com.minesweeper.utils.Printer;

public class PlayMinesweeper {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		char[][] input = { 
				{'x', 'x', 'm'},
				{'x', 'm', 'x'},
				{'x', 'x', 'x'}};
		
		Minesweeper gameDriver = new MinesweeperImpl(input);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int option, row, col;;
		String[] gameInput;
		Printer printer = new ConsolePrinter();
		
		while(!gameDriver.isGameOver()) {
			System.out.println("Enter option: \n(1) Reveal\n(2)Flag\n(3)Clear Around: " );
			option = Integer.parseInt(reader.readLine());
			System.out.println("Enter row and column with a space:");
			gameInput = reader.readLine().split(" ");
			
			row = Integer.parseInt(gameInput[0]);
			col = Integer.parseInt(gameInput[1]);
			
			try {
					switch(option) {
						case 1: {
							gameDriver.reveal(row, col);
							break;
						}
						case 2: {
							gameDriver.flag(row, col);
							break;
						}
						case 3: {
							gameDriver.clearAround(row, col);
							break;
						}
					}
			} catch(IllegalArgumentException e) {
				System.out.println("Invalid input. Please try again.");
			}
		
			printer.printMinefield(gameDriver.getMinefield());
		}
	}
}

