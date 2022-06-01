 

public class ArrayMethods {
	public static int arrayMax(int[] a) {
		int greatest = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > greatest) {
				greatest = a[i];
			}
		}
		return greatest;
	}

	public static int arrayMin(int[] a) {
		int smallest = 101;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < smallest) {
				smallest = a[i];
			}
		}
		return smallest;
	}

	public static void squaredArray(int[] a) {
		int[] squaredArray = a;
		for (int i = 0; i < a.length; i++) {
			squaredArray[i] = a[i] * a[i];
			System.out.print(squaredArray[i] + ", ");
		}
	}

	public static void reverseArray(int[] a) {
		int[] reverseArray = a;
		for (int i = 4; i >= 0; i--) {
			reverseArray[i] = a[i];
			System.out.print(reverseArray[i] + ", ");
		}
	}

	public static void main(String[] args) {
		int i = 0;
		int[] arrayOriginal = new int[5];
		System.out.print("Original Array: ");
		for (i = 0; i < arrayOriginal.length; i++) {
			arrayOriginal[i]   = (int) ((Math.random() * 100) +  1 );
			System.out.print(arrayOriginal[i] + ", ");
		}
		System.out.println("");
		System.out.println("Max value: " + arrayMax(arrayOriginal));
		System.out.println("Min value: " + arrayMin(arrayOriginal));
		System.out.println("Squared array: ");
		squaredArray(arrayOriginal);
		System.out.println(" ");
		System.out.println("Reversed array: ");
		reverseArray(arrayOriginal);

	}
}



