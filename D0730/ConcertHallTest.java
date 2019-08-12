package D0730;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


//������ ��ǰ�ܼ�ƮȦ ����ý����̴�
//- ������ �Ϸ翡 �� �� �ִ�.
//- �¼��� S��, A��,B�����γ�����,���� 10���� �¼��� �ִ�.
//- ���� �ý����� �޴��� "����", "��ȸ", "���", "������"�� �ִ�.
//- ������ �� �ڸ��� �����ϰ�, �¼� Ÿ��, ������ �̸�, �¼� ��ȣ�� ������� �Է¹޾� �����Ѵ�.
//- ��ȸ�� ��� �¼��� ����Ѵ�.
//- ��Ҵ� �������� �̸��� �Է¹޾� ����Ѵ�.
//- ���� �̸�, ���� ��ȣ, ���� �޴�, �߸��� ��� � ���ؼ� ���� �޽����� ����ϰ� ����ڰ� �ٽýõ��ϵ��� �Ѵ�.


class Handler {
	HashMap reser = new HashMap(); //�������̸�, ����
	HashMap con = new HashMap(); //��¥, Concert
	
	//���� �߰��ϱ�
	//������ �Ϸ翡 �� ���� -> ��¥�� key ��
	public void addConcert(Date date, Concert concert) throws Exception {
		if(con.containsKey(date)) {
			throw new Exception("���� ��¥�� �ܼ�Ʈ�� �߰��� �� �����ϴ�.");
		}
		else {
			con.put(date, concert);
		}
	}
	
	//���� �߰��ϱ�
	public void addReservate(String name, Reservate reservate) throws Exception {
		if(reser.containsKey(name)) {
			throw new Exception("�� ����� �� �ڸ��� ������ �� �ֽ��ϴ�.");
		}
		else {
			reser.put(name, reservate);
			reservate.checkSit(name);
		}
	}
	
	//���� ��ȸ�ϱ�
	public void printSit() {
		
		System.out.print("[S��] ");
		for(int i=0;i<10;i++) {
			System.out.print(Reservate.sitS[i] + " ");
		}
		System.out.println();
		System.out.print("[A��] ");
		for(int i=0;i<10;i++) {
			System.out.print(Reservate.sitA[i] + " ");
		}
		System.out.println();
		System.out.print("[B��] ");
		for(int i=0;i<10;i++) {
			System.out.print(Reservate.sitB[i] + " ");
		}
		System.out.println();
	}
	
	//���� ����ϱ�
	public void deleteReservate(String name) {
		if(!reser.containsKey(name)) {
			System.out.println("������ ��ܿ� ���� �̸��Դϴ�.");
		}
		else {
			reser.remove(name);
		}
	}
	
}

//����
class Concert { //�ܼ�Ʈ �̸�, �ܼ�Ʈ ����
	private String conNm;
	private String kind;
	
	Concert(String conNm, String kind){
		this.conNm = conNm;
		this.kind = kind;
	}

	public String getConNm() {
		return conNm;
	}

	public void setConNm(String conNm) {
		this.conNm = conNm;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}

//����
class Reservate {
	private String sitType;
	private int sitNo;
	private int cntS, cntA, cntB;
	
	static String[] sitS = {"___", "___", "___", "___", "___", "___", "___", "___", "___", "___"};
	static String[] sitA = {"___", "___", "___", "___", "___", "___", "___", "___", "___", "___"};
	static String[] sitB = {"___", "___", "___", "___", "___", "___", "___", "___", "___", "___"};
	
	Reservate(String sitType, int sitNo) throws Exception{
		this.sitType = sitType;
		this.sitNo = sitNo;
	}

	public String getSitType() {
		return sitType;
	}

	public void setSitType(String sitType) {
		this.sitType = sitType;
	}

	public int getSitNo() {
		return sitNo;
	}

	public void setSitNo(int sitNo) {
		this.sitNo = sitNo;
	}
	
	//�¼� Ÿ�Դ� 10���� �¼��� �Ѿ�� �ʰ� �ϱ�
	public void checkSit(String name) throws Exception {

		switch(sitType) {
		case "S":
			cntS++;
			sitS[sitNo-1] = name;
			break;
		case "A":
			cntA++;
			sitA[sitNo-1] = name;
			break;
		case "B":
			cntB++;
			sitB[sitNo-1] = name;
			break;
		}
		
		if(cntS > 10 || cntA > 10 || cntB > 10) {
			throw new Exception("�����Ͻ� �¼� Ÿ���� 10�¼��� ��� ���õǾ����ϴ�");
		}
		
	}
	
	
}



public class ConcertHallTest {
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		Scanner sc = new Scanner(System.in);
		
		Handler handler = new Handler();
		Concert concert = new Concert("������ ����ġ��", "���");
		
		handler.addConcert(sdf.parse("2019-08-01"), concert);
		

		while(true) {
			System.out.println();
			System.out.println("�աաաաաաաաաաաաաաաո�ǰ�ܼ�ƮȦ ����ý��ۡաաաաաաաաաաաաաաա�");
			System.out.println("1. ���� / 2. ��ȸ / 3. ���	/ 4. �ý��� ������ >>");
			int num = sc.nextInt();
			
			if(num < 1 || num > 4) {
				System.out.println("�߸��� �����Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
			
			if(num == 1) {
				System.out.println("������ �̸��� �Է��ϼ���.>>");
				String name = sc.next();
				System.out.println("������ �¼� Ÿ�԰� ��ȣ�� �Է��ϼ���.(Ÿ�� : S/A/B, ��ȣ : 1~10)>>");
				String sitType = sc.next();
				int sitNo = sc.nextInt();
				
				Reservate r = new Reservate(sitType, sitNo);
				handler.addReservate(name, r);
				
			} else if(num == 2) {
				System.out.println("�¼��� ��ȸ�մϴ�.");
				handler.printSit();
				
			} else if(num == 3) {
				System.out.println("����� ������ ������ �̸��� �Է��ϼ���.>>");
				String name = sc.next();
				
				handler.deleteReservate(name);
			} else if(num == 4) {
				System.out.println("����ý����� ����˴ϴ�.");
				break;
			}
		}

	}
	
}








