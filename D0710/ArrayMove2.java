package D0710;

import java.util.Scanner;

public class ArrayMove2 {
	private int[] irr;
	int move;
	int[] result = new int[7];

	public ArrayMove2 (int move, int[] irr){
		this.move = move;
		this.irr = irr;
		MoveArray();
	}
	
	public void MoveArray() {
		move = move % irr.length;

		if (move < 0) {
			System.out.println("잘못 입력하셨습니다.");
			System.exit(0);
		} else {
			for (int i = 0; i < move; i++) {
				result[i + move + 1] = irr[i];
			}
			for (int i = move; i < irr.length; i++) {
				result[i - move] = irr[i];
			}
		}
	}
	
	public String toString() {
		return "[" + result[0] + " " + result[1] + " "  + result[2] + " "  + result[3] + " "  + result[4] + " "  + result[5] + " "  + result[6] + "]";
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] irr = { 1, 2, 3, 4, 5, 6, 7 };


		System.out.println("1~10까지의 수 중 하나를 입력하세요.");
		int move = sc.nextInt();
		ArrayMove2 am = new ArrayMove2(move, irr);
		
		//am.MoveArray();
		System.out.println(am);
	}

}
