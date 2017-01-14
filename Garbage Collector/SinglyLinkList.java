package SinglyListListOperations;
import java.util.Arrays;
import java.util.Scanner;



public class SinglyLinkList {

	static LinkedList obj;
	static int totalNumber;
	static int ifDel=0,noOfElements ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter the no of elements:");
		
		Scanner reader = new Scanner(System.in);  
		noOfElements = reader.nextInt();
		
		obj=new LinkedList(noOfElements);
		totalNumber=noOfElements;
		boolean loop=true;
		int number;


		while(loop)
		{
			/***
			 * Menu for all operations
			 * All keys, Next pointer and head gets printed after every operation
			 * -1 in key and next pointer denote that this location had been used 
			 * earlier and the delete operation has been done on it.
			 */
			System.out.println("---------Menu-------");
			System.out.println(("1. Insert"));
			System.out.println(("2. Delete"));
			System.out.println(("3. Member function"));
			
			System.out.println(("4. Exit"));
			System.out.println("Select the operation to perform: ");
			
			reader = new Scanner(System.in);  // Reading from System.in
			int operation = reader.nextInt(); // Scans the next token of the input as an int.	
			switch (operation) {
			case 1:
				System.out.println("Enter the number to push: ");
				number = reader.nextInt();
				int call=Insert(number);
				if(call==1)
				{
					System.out.println("Error number not inserted");
				}
				System.out.println(Arrays.toString(obj.Key));
				System.out.println(Arrays.toString(obj.Next));
				System.out.println("Head Key: "+obj.headKey+ "  Head Next "+obj.headNext);
				break;
			case 2:
				
				System.out.println("Enter the number to delete: ");
				number = reader.nextInt();
				call= Delete(number);
				if(call==1)
				{
					System.out.println("Error: number not deleted as not found");
				}
				else if(call==2)
				{
					System.out.println("List is empty");
				}
				System.out.println(Arrays.toString(obj.Key));
				System.out.println(Arrays.toString(obj.Next));
				System.out.println("Head Key: "+obj.headKey+ "  Head Next: "+obj.headNext);
				
				break;
			case 3:
				System.out.println("Enter the number to check: ");
				number = reader.nextInt();
				call= member(number);
				if(call==1)
				{
					System.out.println("Error number not present");
				}
				else
				{
					System.out.println("Number present in List");
				}
				break;
			case 4:
				loop=false;
				break;
			
			default:
				System.out.println("Please select anyone of the above mentioned numbers");
				break;
			}
		}
		
		}
	
	/***
	 * Function to delete keys and corresponding pointer to the key.
	 * Every deleted location is added into free array.
	 * value 9999 is assumed as NULL pointer
	 * @param number 
	 * (number= key) to be deleted.
	 * @return
	 * Returns 1 if list is empty, returns 2 is elements could not be found
	 * and 0 on successful deletion
	 */
	static int Delete(int number)
	{
		// Condition to handle if user tries to delete head
		if(obj.headKey==number)
			{
				if(obj.headNext==9999)
				{
					int i;
					// If all elements all deleted then both key and next array 
					//is reset to contain all 0s.
					for(i=0;i<obj.free.length-1;i++)
					{
						obj.free[i]=i;
						obj.Next[i]=0;
						obj.Key[i]=0;
					}
					obj.free[i]=i;
					obj.freeIndex=0;
					obj.headKey=0;
					obj.headNext=0;
					return 0;
				}
				else
				{
			
					// Condtion to handle the situation if head has been moved 
					//from initial position
					obj.Key[obj.lastIndex]=-1;
					obj.Next[obj.lastIndex]=-1;
					obj.freeIndex--;
					obj.free[obj.freeIndex]=obj.lastIndex;
					obj.lastIndex=obj.headNext;
					obj.headKey=obj.Key[obj.lastIndex];
					obj.headNext = obj.Next[obj.lastIndex];
					
					// New condition
					if(obj.headNext==9999)
						return 0;
					
					
					if(obj.headNext==-1)
					{
						int i;
						for(i=0;i<obj.free.length-1;i++)
						{
							obj.free[i]=i;
							obj.Next[i]=0;
							obj.Key[i]=0;
						}
						obj.free[i]=i;
						obj.freeIndex=0;
						obj.headKey=0;
						obj.headNext=0;
						return 0;
					}
					
					
				}
			}
			else
			{
				int count=0;
				int tempPrev=obj.lastIndex;
				int tempNext=obj.headNext;
				int fix=obj.Next[tempPrev];
				for(int i=0;i<obj.freeIndex;i++)
				{
					fix=obj.Next[tempPrev];
					if(obj.Next[tempPrev]==9999)
						fix=0;
					if(obj.Key[fix]==number)
					{
						if(obj.Next[tempNext]==9999)
						{
							obj.Next[tempPrev]=9999;
							obj.Key[tempNext]=-1;
							obj.Next[tempNext]=-1;
							obj.freeIndex--;
							obj.free[obj.freeIndex]=tempNext;
							return 0;
						}
						else
						{
						obj.Key[tempNext]=-1;
						obj.Next[tempPrev]=obj.Next[tempNext];
						obj.Next[tempNext]=-1;
						obj.freeIndex--;
						obj.free[obj.freeIndex]=tempNext;
						//obj.lastIndex=tempNext;
						
						if(count==0)
						{
							obj.headNext=obj.headNext-1;
						}
						
						return 0;
						}
					}
					count++;
					tempPrev=tempNext;
					if(tempPrev==0 || tempNext==9999)
					{
						return 1;
					}
						
					tempNext=obj.Next[tempNext];
				}
				
			}
			
			
		
		return 2;
	}
	
	
	/***
	 * Checks if key present in the Array.
	 * @param number
	 * @return
	 * returns 1 if not present otherwise 0
	 */
	static int member(int number)
	{
		int temp=obj.lastIndex;
		for(int i=0;i<obj.freeIndex;i++)
		{
			if(obj.Key[temp]==number)
			{
				
				return 0;
			}
			temp=obj.Next[temp];
			
		}
		
		return 1;
	}
	
	
	/***
	 * Inserts a key into the next free location
	 * Free location is taken from freeIndex variable. This freeIndex variable takes
	 * values from Free[] array. Free array keeps track of all free locations.
	 * @param number
	 * number to be inserted into the array
	 * @return
	 * returns 1 on any error and 0 on successful insertion.
	 */
	static int Insert(int number)
	{
		// checks if List has free location
		if(obj.free[obj.freeIndex]<noOfElements)
		{
			if(obj.freeIndex==totalNumber)
			{
				System.out.println("Error");
				return 1;
			}
			// Inserts the 1st element
			else if(obj.freeIndex==0)
			{
				obj.Key[obj.free[obj.freeIndex]]=number;
				obj.Next[obj.free[obj.freeIndex]]=obj.nullIndex;
				
				obj.lastIndex=obj.free[obj.freeIndex];
				
					obj.headKey=obj.Key[obj.free[obj.freeIndex]];
					obj.headNext=obj.Next[obj.free[obj.freeIndex]];
			
				System.out.println(obj.headKey);
				System.out.println(obj.headNext);
				obj.freeIndex++;
				return 0;
			}
			else
			{
				obj.Key[obj.free[obj.freeIndex]]=number;
				obj.Next[obj.free[obj.freeIndex]]=obj.lastIndex;
				obj.headKey=obj.Key[obj.free[obj.freeIndex]];
				obj.headNext=obj.Next[obj.free[obj.freeIndex]];
				obj.lastIndex=obj.free[obj.freeIndex];
				obj.freeIndex++;
				return 0;
			}		
		}
		else
		{
			System.out.println("List is full");
			return 1;
		}
	}
		
}
