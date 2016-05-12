package soham.practice.heap.rangeOfFrequencyOfAllWords;

import lombok.Getter;

@Getter
public class HeapNode {
	private int wordArrayIndex;
	private int data;
	private int currentPosInArray;
	
	public HeapNode(int arrayIndex, int data) {
		this.wordArrayIndex = arrayIndex;
		this.data = data;
		this.currentPosInArray = 0;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void incrementCurrentPosInArray(){
		this.currentPosInArray++;
	}
}
