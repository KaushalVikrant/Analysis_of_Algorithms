import java.util.Random;
import java.util.Arrays;

public class Merge_Insert_Evaluation {
	public int mergeComparision=0;

	public static int insertion_sort(int[] arr)
	{
		int comparison_count=0;
		int length=arr.length;
		for(int i=0;i<length-1;i++)
		{
			int j=i+1;
			int key=arr[j];
			while(j>=1&&comparison_count==comparison_count++&&key<arr[j-1])
			{
				int temp=key;
				arr[j]=arr[j-1];
				arr[j-1]=temp;
				j--;
			}
		}
		return comparison_count;
}

	public  void doMerge(int[] arr, int left,int leftHigh,int rightLow,int rightHigh)
	{
		int i;
		int start=left;
		int[] temp=new int[101];
		for(i=left;i<=rightHigh;i++)
		{
			if(left<=leftHigh && rightLow<=rightHigh)
			{	
				mergeComparision++;
				if(arr[left]<arr[rightLow])
					temp[i]=arr[left++];
				else 
					temp[i]=arr[rightLow++];
			}
			else if(left>leftHigh )
			{
				temp[i]=arr[rightLow++];
			}
			else
			{
				temp[i]=arr[left++];
			}
		}
		for(int c=start;c<=rightHigh;c++)
		{
			arr[c]=temp[c];
		}
	}
	public  void merge_sort(int[] arr, int start, int end)
	{
		if(start<end)
		{
				int mid=(int)(Math.floor((start+end)/2));
				merge_sort(arr,start,(mid));
				merge_sort(arr, mid+1, end);
				doMerge(arr,start,mid,mid+1,end);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Merge_Insert_Evaluation obj=new Merge_Insert_Evaluation();
		for(int i=5;i<=100;i=i+5)
		{
			int[] arr=new int[i];
			int[] arr2=new int[i];
			int comparisionsInsert=0;
			int[] insertion_result=new int[101];
			for(int count=0;count<100;count++)
			{
				for(int j=0;j<arr.length;j++)
				{
					arr[j]=(int)(Math.random()*100);
					arr2[j]=(int)(Math.random()*100);
				}
						comparisionsInsert+=insertion_sort(arr);
						obj.merge_sort(arr2, 0, arr.length-1);
			}
			
			insertion_result[i]=comparisionsInsert/100;
			System.out.println("Insertion sort comparisons for arr["+i+"] is: "+comparisionsInsert+"/100= "+insertion_result[i]);
			System.out.println("Merge sort comparisons for arr["+i+"] is: "+obj.mergeComparision+"/100= "+obj.mergeComparision/100);
			obj.mergeComparision=0;
		}

		}
	}

