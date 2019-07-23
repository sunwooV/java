package D0708;

//내림차순 정렬하기
//int[] score = {79,88,91,33,100,55,95};

//for(int k=0;k<score.length;k++)
//	System.out.println(numArr[k]);; //정렬된 결과를 출력한다.

public class ArrayEx10 {

	public static void main(String[] args) {
		int[] score = { 79, 88, 91, 33, 100, 55, 95 };

		int temp;
		for (int i = 0; i < score.length; i++) {
			for (int j = i + 1; j < score.length; j++) {
				if (score[i] < score[j]) {
					temp = score[j];
					score[j] = score[i];
					score[i] = temp;
				}
			}
		}

		for (int k = 0; k < score.length; k++) {
			System.out.println(score[k]);
		}

	}

}
