package soham.practice.heap.rangeOfFrequencyOfAllWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * O(NlogK)
 * N = total number of frequeny entries combines
 * K = number of words
 * */

public class DriverClass {
	
	private static HeapNode[] minHeap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	
		System.out.print("Enter the number of words: ");
    	int N = Integer.parseInt(reader.readLine());
    	
    	int[][] arr = new int[N][];
    	for(int i=0; i<N; i++) {
    		System.out.println("Enter the frequency of word " + (i+1) + " as space separated list:" );
    		String[] input = reader.readLine().split(" ");
    		int len = input.length;
    		arr[i] = new int[len];
    		for(int j=0; j<len; j++) {
    			arr[i][j] = Integer.parseInt(input[j]);
    		}
    	}
    	
    	printMinimumRange(arr, N);
	}

	private static void printMinimumRange(int[][] arr, int n) {
		int prevMax = createMinHeapAndReturnMaxVal(arr, n);
		int currMax, currMin;
		int resLowRange = minHeap[0].getData();
		int resHighRange = prevMax;
		
		HeapNode tempNode = minHeap[0];
		int wordIndex= tempNode.getWordArrayIndex();
		int i = tempNode.getCurrentPosInArray() + 1;
		while(i < arr[wordIndex].length) {
			
			insertDataToHeap(0, arr[wordIndex][i]);
			currMax = Math.max(prevMax, arr[wordIndex][i]);
			heapifyDown(0, n-1);
			currMin = minHeap[0].getData();
			
			if((currMax-currMin) < (resHighRange-resLowRange)) {
				resHighRange = currMax;
				resLowRange = currMin;
			}
			
			tempNode = minHeap[0];
			wordIndex= tempNode.getWordArrayIndex();
			i = tempNode.getCurrentPosInArray() + 1;
			prevMax = currMax;
		}
		
		System.out.println("The minimum range of frequencies in which all words lie are: ["
				+ resLowRange + "," + resHighRange + "]");
	}

	private static int createMinHeapAndReturnMaxVal(int[][] arr, int n) {
		minHeap = new HeapNode[n];
		int max = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			insertDataToHeap(i, arr[i][0]);
			heapifyUp(i);
			max = Math.max(arr[i][0], max);
		}
		
		return max;
	}

	private static void insertDataToHeap(int arrayIndex, int data) {
		if(minHeap[arrayIndex] == null)
			minHeap[arrayIndex] = new HeapNode(arrayIndex, data);
		else {
			minHeap[arrayIndex].setData(data);
			minHeap[arrayIndex].incrementCurrentPosInArray();
		}
	}
	
	private static void heapifyUp(int pos) {
		int parent = getParent(pos);
		if(pos>0 && parent>=0 && minHeap[pos].getData() < minHeap[parent].getData()) {
			swapElements(pos, parent);
			heapifyUp(parent);
		}	
	}
	
	private static void heapifyDown(int fromPos, int toPos) {
		if(fromPos>=toPos)
			return;
		
		int leftChild = getLeftChild(fromPos);
		int rightChild = getRightChild(fromPos);
		int minChild = 0;
		
		if(leftChild <= toPos && rightChild <= toPos)
			minChild = minHeap[leftChild].getData() < minHeap[rightChild].getData() ? 
						leftChild : rightChild;
		else if(leftChild <= toPos && rightChild > toPos)
			minChild = leftChild;
		else
			return;
		
		if(minHeap[minChild].getData() < minHeap[fromPos].getData()){
			swapElements(minChild, fromPos);
			heapifyDown(minChild, toPos);
		}
	}
	
	private static int getParent(int pos) {
		return (pos%2==0) ? (pos/2)-1 : pos/2;
	}	
	
	private static int getLeftChild(int i) {
		return 2*i+1;
	}
	
	private static int getRightChild(int i) {
		return 2*i+2;
	}
	
	private static void swapElements(int source, int destination) {
		HeapNode temp = minHeap[source];
		minHeap[source] = minHeap[destination];
		minHeap[destination] = temp;
	}
}
