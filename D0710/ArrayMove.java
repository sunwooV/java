package D0710;

import java.util.Scanner;

public class ArrayMove {
	int num;
	int[] irr;
	int[] result = { 0, 0, 0, 0, 0, 0, 0 };
	
	public ArrayMove(int num, int[] irr) {
		this.num = num;
		this.irr = irr;
	}

	public void Move() {
		int move = num % irr.length;


		if (num < 0) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			System.exit(0);
		} else {
			for (int i = 0; i < move; i++) {
				result[i + move + 1] = irr[i];
				// move = 3
				// 0->4
				// 1->5
				// 2->6
			}
			for (int i = move; i < irr.length; i++) {
				result[i - move] = irr[i];
			}
		}
	}
	
	public void Print() {
		System.out.print("[ ");
		for (int j = 0; j < result.length; j++) {
			System.out.print(result[j] + " ");
		}
		System.out.print("]");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		int[] irr = { 1, 2, 3, 4, 5, 6, 7 };

		System.out.println("1~10������ �� �� �ϳ��� �Է��ϼ���.");
		int num = sc.nextInt();
		ArrayMove am = new ArrayMove(num, irr);

		am.Move();
		am.Print();

	}

}
