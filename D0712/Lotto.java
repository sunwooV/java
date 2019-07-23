package D0712;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {
		Lotto lotto = new Lotto();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�ζǹ�ȣ ���� ���� �Է�:");
		int gameCnt = sc.nextInt();
		
		for(int i=1;i<=gameCnt;i++) {
			System.out.println(i + "��° �ζǹ�ȣ:" + lotto.lottoNumbers());
		}
	}
	
	String lottoNumbers() {
		List<Integer> lottoNum = new ArrayList<Integer>();
		
		//List �ȿ� �ζǹ�ȣ �߰�
		for(int i=1;i<=45;i++) {
			lottoNum.add(i);
		}
		
		//set���� ���� �������� ���´�.
		Collections.shuffle(lottoNum);
		
		int[] lottoNums = new int[6];
		for(int i=0;i<lottoNums.length;i++) {
			lottoNums[i] = lottoNum.get(i);
		}
		
		//����
		Arrays.sort(lottoNums);
		
		return Arrays.toString(lottoNums);
	}

}
