package D0717;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysEx {

	public static void main(String[] args) {
		
		//�迭 ����
		int[] arr = {0,1,2,3,4};
		int[] arr2 = Arrays.copyOf(arr, arr.length); //��ü����ŭ ����
		int[] arr3 = Arrays.copyOfRange(arr, 2, 4); //2<= <4
		
		System.out.println("arr2");
		print(arr2);
		System.out.println("arr3");
		print(arr3);
		
		//�迭 ä��
		int[] arr4 = new int[5];
		Arrays.fill(arr4, 9); //9,9,9,9,9
		System.out.println("arr4");
		print(arr4);
		
		//binarySearch(arr, value) : ������� �˻�
		int[] arr5 = {3,2,5,1,4};
		Arrays.sort(arr5); //0,1,2,3,4
		int idx = Arrays.binarySearch(arr5, 2); //2�� �ִ� index ��ȯ
		System.out.println("arr5");
		print(arr5);
		
		//equals(arr, arr2):��� ��� ��
		//toString() : ����� ���, 1������
		//deepToString(): �������迭 ���
		int[] arr6 = {0,1,2,3,4};
		int[][] arr7 = {{11,12},{21,22}};
		System.out.println(Arrays.toString(arr6));
		System.out.println(Arrays.deepToString(arr7));
		
		//�迭�� List�� ��ȯ
		//���游 �����ϰ� �߰�/������ �Ұ����� List��ȯ
		List<Integer> list = Arrays.asList(new Integer[] {1,2,3,4,5});
		//�߰�/���� �����ϰ� �Ϸ��� �Ʒ�ó��
		List list2 = new ArrayList(Arrays.asList(1,2,3,4,5));
		
		System.out.println(list);
		System.out.println(list2);
		
	}

	static void print(int[] obj) {
		for(int i=0;i<obj.length;i++) {
			System.out.print(obj[i] + " ");
		}
		System.out.println();
	}
}
