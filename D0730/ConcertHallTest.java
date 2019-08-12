package D0730;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


//다음은 명품콘서트홀 예약시스템이다
//- 공연은 하루에 한 번 있다.
//- 좌석은 S석, A석,B석으로나뉘며,각각 10개의 좌석이 있다.
//- 예약 시스템의 메뉴는 "예약", "조회", "취소", "끝내기"가 있다.
//- 예약은 한 자리만 가능하고, 좌석 타입, 예약자 이름, 좌석 번호를 순서대로 입력받아 예약한다.
//- 조회는 모든 좌석을 출력한다.
//- 취소는 예약자의 이름을 입력받아 취소한다.
//- 없는 이름, 없는 번호, 없는 메뉴, 잘못된 취소 등에 대해서 오류 메시지를 출력하고 사용자가 다시시도하도록 한다.


class Handler {
	HashMap reser = new HashMap(); //예약자이름, 예약
	HashMap con = new HashMap(); //날짜, Concert
	
	//공연 추가하기
	//공연은 하루에 한 번만 -> 날짜를 key 로
	public void addConcert(Date date, Concert concert) throws Exception {
		if(con.containsKey(date)) {
			throw new Exception("같은 날짜에 콘서트를 추가할 수 없습니다.");
		}
		else {
			con.put(date, concert);
		}
	}
	
	//예약 추가하기
	public void addReservate(String name, Reservate reservate) throws Exception {
		if(reser.containsKey(name)) {
			throw new Exception("한 사람당 한 자리만 예약할 수 있습니다.");
		}
		else {
			reser.put(name, reservate);
			reservate.checkSit(name);
		}
	}
	
	//예약 조회하기
	public void printSit() {
		
		System.out.print("[S석] ");
		for(int i=0;i<10;i++) {
			System.out.print(Reservate.sitS[i] + " ");
		}
		System.out.println();
		System.out.print("[A석] ");
		for(int i=0;i<10;i++) {
			System.out.print(Reservate.sitA[i] + " ");
		}
		System.out.println();
		System.out.print("[B석] ");
		for(int i=0;i<10;i++) {
			System.out.print(Reservate.sitB[i] + " ");
		}
		System.out.println();
	}
	
	//예약 취소하기
	public void deleteReservate(String name) {
		if(!reser.containsKey(name)) {
			System.out.println("예약자 명단에 없는 이름입니다.");
		}
		else {
			reser.remove(name);
		}
	}
	
}

//공연
class Concert { //콘서트 이름, 콘서트 종류
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

//예약
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
	
	//좌석 타입당 10개의 좌석을 넘어가지 않게 하기
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
			throw new Exception("선택하신 좌석 타입의 10좌석이 모두 선택되었습니다");
		}
		
	}
	
	
}



public class ConcertHallTest {
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		Scanner sc = new Scanner(System.in);
		
		Handler handler = new Handler();
		Concert concert = new Concert("유희열의 스케치북", "방송");
		
		handler.addConcert(sdf.parse("2019-08-01"), concert);
		

		while(true) {
			System.out.println();
			System.out.println("≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡명품콘서트홀 예약시스템≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡");
			System.out.println("1. 예약 / 2. 조회 / 3. 취소	/ 4. 시스템 끝내기 >>");
			int num = sc.nextInt();
			
			if(num < 1 || num > 4) {
				System.out.println("잘못된 숫자입니다. 다시 입력해주세요.");
				continue;
			}
			
			if(num == 1) {
				System.out.println("예약할 이름을 입력하세요.>>");
				String name = sc.next();
				System.out.println("예약할 좌석 타입과 번호를 입력하세요.(타입 : S/A/B, 번호 : 1~10)>>");
				String sitType = sc.next();
				int sitNo = sc.nextInt();
				
				Reservate r = new Reservate(sitType, sitNo);
				handler.addReservate(name, r);
				
			} else if(num == 2) {
				System.out.println("좌석을 조회합니다.");
				handler.printSit();
				
			} else if(num == 3) {
				System.out.println("취소할 예약의 예약자 이름을 입력하세요.>>");
				String name = sc.next();
				
				handler.deleteReservate(name);
			} else if(num == 4) {
				System.out.println("예약시스템이 종료됩니다.");
				break;
			}
		}

	}
	
}








