

public class SimpleSort {
	public static void BubbleSort(int[] a) {
		int[] b = a.clone();
		int compare = 0;
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = 0; j < b.length - i - 1; j++) {
				if (b[j] > b[j + 1]) {
					compare++;
					int temp = b[j + 1];
					b[j + 1] = b[j];
					b[j] = temp;
				}
			}
		}
		
		System.out.print("Bubble sorted values: ");
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println(" ");
		System.out.println("Number of comparisons from bubble sort: " + compare);
	}
	public static void InsertionSort (int[] a)
	{
		int [] c = a.clone();
		int compare = 0;
	   for (int i=1; i < c.length; i++)
	   {
	      int key = c[i];
	      int position = i;
	      while (position > 0 && key < c[position-1]) 
	      {
	         c[position] = c [position - 1];
	         position--;
	      }
	      compare++;
	      c [position] = key;
	   }
	  
		System.out.print("Insertion sorted values: ");
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + " ");
		}
		System.out.println(" ");
		System.out.println("Number of comparisons from insertion sort: " + compare);
	}
	public static void SelectionSort(int[] a) {
		int[] d = a.clone();
		int compare = 0;
	int minPos = 0;
		for (int i = 0; i < d.length; i++) {
			minPos = i;
			
			for (int j = i+ 1; j  < d.length ; j++) {
				if (d[j] < d[minPos]) {
					compare++;
					minPos = j;
				}
			}
			
			if(i != minPos && minPos < d.length) {
				compare++;
				int temp = d[minPos];
				d[minPos] = d[i];
				d[i] = temp;
			}
		}
	
		System.out.print("Selection sorted values: ");
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i] + " ");
		}
		System.out.println(" ");
		System.out.println("Number of comparisons from selection sort: " + compare);
	}
	public static void main(String[] args) {
		int[] array = new int[50];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) ((Math.random() * 100)+0);
		}
		System.out.print("Array values :");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println(" ");
		SimpleSort.BubbleSort(array);
		SimpleSort.InsertionSort(array);
		SimpleSort.SelectionSort(array);
	}
}

