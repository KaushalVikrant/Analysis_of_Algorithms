package SinglyListListOperations;

public class LinkedList {
	//Array of keys
	int[] Key;
	//Arrays of next locations
	int[] Next;
	
	// length= length of all the arrays
	//nullIndex is a variable signifies null with 9999 value
	// lastIndex is the location where last key had been added
	int length,nullIndex,lastIndex;
	// Array to keep free locations
	int free[];
	// key in the head node
	int headKey;
	// Next address in head node
	int headNext;
	// Free index where next value should be inserted
	int freeIndex;
	
	public LinkedList(int n)
	{
		Key=new int[n];
		Next=new int[n];
		free=new int[n+1];
		for(int i=0;i<free.length;i++)
		{
			free[i]=i;
		}
		
		
		freeIndex=0;
		length=n-1;
		nullIndex=9999;
				
	}

}
