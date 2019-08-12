package D0708;

//최대값/최소값 구하기

//int[] score = {79,88,91,33,100,55,95};

//System.out.println("최대값 : " + max );
//System.out.println("최소값 : " + min);
public class ArrayEx6 {

	public static void main(String[] args) {
		int[] score = {79, 88, 91, 33, 100, 55, 95};
		int max = score[0], min = score[0];
		
		for(int i=0;i<score.length;i++) {
			if(max<score[i]) {
				max = score[i];
			}
			else if(min>score[i]) {
				min = score[i];
			}
					
			
		}

		System.out.println("최대값 : " + max);
		System.out.println("최소값 : " + min);
	}

}
