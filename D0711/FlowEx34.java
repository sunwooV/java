package D0711;

import java.util.Scanner;

public class FlowEx34 {
	public static void main(String[] args) {
		int menu = 0;
		int num = 0;
		
		Scanner sc = new Scanner(System.in);
		
		outer: //반복문에 이름을 부여 -> 별로 쓰지 않는 것을 추천한다. 
			while(true) {
				System.out.println("(1) square"); //제곱
				System.out.println("(2) square root"); //루트
				System.out.println("(3) log"); //log
				System.out.print("원하는 메뉴(1~3)를 선택하세요.(종료:0)> ");
				
				String tmp = sc.nextLine();
				menu = Integer.parseInt(tmp);
				
				if(menu==0) {
					System.out.println("프로그램을 종료합니다.");
					break;
				} else if(!(1<=menu && menu <= 3)) {
					System.out.println("메뉴를 잘못 선택하셨습니다.(종료는 0)");
					continue;
				}
				
				for(;;) {
					System.out.print("계산할 값을 입력하세요.(계산 종료:0, 전체 종료:99)> ");
					tmp = sc.nextLine();
					num = Integer.parseInt(tmp);
					
					if(num==0)
						break; //계산 종료. for문을 벗어난다.
					if(num==99) {
						System.out.println("프로그램을 종료합니다.");
						break outer; //전체 종료. for문과 while문을 모두 벗어난다.
					}
					
					switch(menu) {
					case 1:
						System.out.println("result = "+ num*num);
						break;
					case 2:
						System.out.println("result = " + Math.sqrt(num));
						break;
					case 3:
						System.out.println("result = " + Math.log(num));
						break;
//					default:
//						System.out.println("메뉴를 잘못 선택하셨습니다.(종료는 0)");
//						continue;
					}
				}
			}
	}
}
