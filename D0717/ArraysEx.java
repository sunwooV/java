package D0717;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysEx {

	public static void main(String[] args) {
		
		//배열 복사
		int[] arr = {0,1,2,3,4};
		int[] arr2 = Arrays.copyOf(arr, arr.length); //객체수만큼 복사
		int[] arr3 = Arrays.copyOfRange(arr, 2, 4); //2<= <4
		
		System.out.println("arr2");
		print(arr2);
		System.out.println("arr3");
		print(arr3);
		
		//배열 채움
		int[] arr4 = new int[5];
		Arrays.fill(arr4, 9); //9,9,9,9,9
		System.out.println("arr4");
		print(arr4);
		
		//binarySearch(arr, value) : 지정요소 검색
		int[] arr5 = {3,2,5,1,4};
		Arrays.sort(arr5); //0,1,2,3,4
		int idx = Arrays.binarySearch(arr5, 2); //2가 있는 index 반환
		System.out.println("arr5");
		print(arr5);
		
		//equals(arr, arr2):모든 요소 비교
		//toString() : 모든요소 출력, 1차원만
		//deepToString(): 다차원배열 출력
		int[] arr6 = {0,1,2,3,4};
		int[][] arr7 = {{11,12},{21,22}};
		System.out.println(Arrays.toString(arr6));
		System.out.println(Arrays.deepToString(arr7));
		
		//배열을 List로 변환
		//변경만 가능하고 추가/삭제가 불가능한 List반환
		List<Integer> list = Arrays.asList(new Integer[] {1,2,3,4,5});
		//추가/삭제 가능하게 하려면 아래처럼
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
