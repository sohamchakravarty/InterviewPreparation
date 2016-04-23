package com.minesweeper.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationData {
	private int data;
	private UserInput userData;
	
	public LocationData() {
		this.data = 0;
	}
	
	public boolean isMine() {
		return this.data == -1;
	}
	
	public boolean isOpen() {
		return (this.userData != null && this.userData.equals(UserInput.OPEN));
	}
	
	public boolean isFlagged() {
		return (this.userData!=null && this.userData.equals(UserInput.FLAG));
	}
}
