package D0711;

import java.util.Scanner;

public class FlowEx11 {

	public static void main(String[] args) {
		char gender;
		String regNo = "";
		System.out.println("����� �ֹι�ȣ�� �Է��ϼ���.(011231-1111222)>");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		regNo = scanner.nextLine();
		
		gender = regNo.charAt(7); //�Է¹��� ��ȣ�� 8��° ���ڸ� gender�� ����
		
		switch(gender) {
		case '1':
		case '3':
			switch(gender) {
			case '1':
				System.out.println("����� 2000�� ������ ����� �����Դϴ�.");
				break;
			case '3':
				System.out.println("����� 2000�� ���Ŀ� ����� �����Դϴ�.");
				break;
			}
			break;
		case '2':
		case '4':
			switch(gender) {
			case '2':
				System.out.println("����� 2000�� ������ ����� �����Դϴ�.");
				break;
			case '4':
				System.out.println("����� 2000�� ���Ŀ� ����� �����Դϴ�.");
				break;
			}
			break;
		default:
			System.out.println("��ȿ���� ���� �ֹε�Ϲ�ȣ�Դϴ�.");
		}

	}

}
