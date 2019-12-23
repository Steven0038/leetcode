package _5.leetcode_sort_and_search;

/**
 * Best O(n), AVG(n^2), WOST(n^2)
 * @author steven
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		int arr[] = { 12, 11, 13, 5, 6 };

		Is ob = new Is();
		ob.sort(arr);

		printArray(arr);
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");

		System.out.println();
	}

}

class Is {

	/* Function to sort array using insertion sort */
	public void sort(int arr[]) {
		// int arr[] = { 12, 11, 13, 5, 6 };
		for (int i = 1; i < arr.length; ++i) {
			int key = arr[i];//�@�i�J i=1,�q11�}�l��,���arr[4],�]�N�O6
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];// �u�n�o�{ key �e�@�Ӥ�����key�j, �N����쥻 key ����m(j+1), j �N�O key����m -1(i-1)
				j--; // while �������, j ���e�� �~��V�e�ˬd
			}
			arr[j + 1] = key;// ���� j ���� array �̫e���Ϊ̵o�{�S���� key �j�F, �N�� key ��^�h(�p�G�쩳j�|�O-1)
		}
	}
}
