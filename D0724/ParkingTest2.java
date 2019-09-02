//1. ParkingLotAdm (주차장관리) 
//
//- 속성: private Date startDt, private Date endDt, private final int maxCarsu
//
//         private (?) cars, private (?) outCars, private (?) waitCars
//
//- 생성자:
//
//ParkingLotAdm (Date statDt, Date endDt, int maxCarsu)
//
//- 메소드
//
//public void addCar(Car car): 주차하는 차를 추가한다
//
//public int outCar(Car car): 나가는 차를 제거하면 outCars에 추가하고 주차비용을 리턴한다
//
//                          주차비용은 30분당 500원 이다
//
//                          주차대기중인 차가 있으면 주차시킨다(주차대기중인 차는 대기순서에 따라 주차)
//
//public void carSort(Comparator c): 파라미터로 전달한 정렬기준에 따라 주차차량을 Sort한다
//
//public Collection selectCheck(int hour): hour시간 이상 주차중인 차를 리턴한다
//
//public int getTotalFee(): 총 주차수수료 합을 리턴한다
//
//
//
//2. Car
//
//- 속성: private String carNo, private Date inDt(입차시간), private Date outDt(출차시간), private int fee
//
//        private Date waitStrDt(대기시작시간)
//
package D0724;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

//주차장 관리
class ParkingLotAdm2 {
	private Date startDt;
	private Date endDt;
	private final int maxCarsu;
	private HashMap cars;
	private HashMap outCars;
	private Queue waitCars = new LinkedList();
	private ArrayList oldCars = new ArrayList();
	private int count = 0; //주차된 차량 수
	private int count2 = 0; //아웃된 차량 수
	Utils2 u = new Utils2();
	Cal c = new Cal();
	
	ParkingLotAdm2(Date startDt, Date endDt, int maxCarsu){
		this.startDt = startDt;
		this.endDt = endDt;
		this.maxCarsu = maxCarsu;
		cars = new HashMap();
		outCars = new HashMap();
	}
	
	//주차하는 차를 추가한다.
	public void addCar(Car2 car2) {
		
		//주차장 꽉 차지 않았을 때
		if(count < maxCarsu) {
			String value = car2.getInDt() + "" + car2.getOutDt();
			cars.put(car2.getCarNo(), value);
			System.out.println("[" + u.getDate(car2.getInDt()) + "] " + car2.getCarNo() + " 차가 주차장에 들어갑니다.");
			count++;
		}
		
		//주차장이 꽉 찼을 때
		else {
			waitCars.offer(car2);
			System.out.println("[" + u.getDate(car2.getInDt()) + "] " + car2.getCarNo() + " 차가 대기합니다.");
		}
		
	}
	
	//나가는 차를 제거하면 outCars에 추가하고 주차비용을 리턴한다.
	//주파비용은 30분당 500원이다.
	//주차대기중인 차가 있으면 주차시킨다(주차대기중인 차는 대기순서에 따라 주차)
	public int outCar(Car2 car2) {

		if(cars.containsKey(car2.getCarNo())) {
			String value = car2.getInDt() + "" + car2.getOutDt();
			outCars.put(car2.getCarNo(), value);
			System.out.println();
			System.out.println("[" + u.getDate(car2.getOutDt()) + "] " + car2.getCarNo() + " 차가 주차장을 나갑니다.");
			count2++;
				
			cars.remove(car2.getCarNo());

		}
		
		//기다리는 차가 있으면
		if(!waitCars.isEmpty()) {
			Object p = waitCars.poll();
			String value = car2.getOutDt() + "" + ((Car2)p).getOutDt();
			cars.put(((Car2)p).getCarNo(), value);
			
			
			System.out.println();
			System.out.println("[" + u.getDate(car2.getOutDt()) + "] " + ((Car2)p).getCarNo() + " 차가 대기를 끝내고 주차장에 들어갑니다.");
			count++;
		}
		
		//주차비용 리턴
		int fee = c.feeCal(car2);
		
		return fee;
	}
	
	//파라미터로 전달한 정렬기준에 따라 주차차량을 Sort한다.
	public void carSort(Comparator c) {
		List list = new ArrayList();
        list.addAll(cars.keySet());
         
        Collections.sort(list, c);
        
        for(int i=0;i<list.size();i++) {
        	System.out.println(list.get(i));
        }
	}
	
//	//hour시간 이상 주차중인 차를 리턴한다.
//	public Collection selectCheck(int hour) {
//		for(int i=0;i<count;i++) {
//			if(cars[i].GetTime() >= hour) {
//				oldCars.add(cars[i]);
//			}
//		}
//		return oldCars;
//	}
	
	
//	//총 주차수수료 합을 리턴한다.
//	public int getTotalFee() {
//		int total = 0;
//		for(int i=0;i<outCars.length;i++) {
//			total += (int) outCars[i].GetTime();
//		}
//		return total;
//		
//	}
}

class comparing implements Comparator <Car2> {

	@Override
	public int compare(Car2 o1, Car2 o2) {
		int result = ((Car2)o1).getCarNo().compareTo(((Car2)o2).getCarNo());
		return result;
	}
	
}

class Car2 {
	private String carNo;
	private Date inDt; //입차시간
	private Date outDt; //출차시간
	private int fee; //요금
	private Date waitStrDt; //대기 시작 시간
	
	Car2(){}
	
	Car2(String carNo){
		this.carNo = carNo;
	}
	
	Car2(String carNo, Date inDt, Date outDt, Date waitStrDt){
		this.carNo = carNo;
		this.inDt = inDt;
		this.outDt = outDt;
		this.waitStrDt = waitStrDt;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Date getInDt() {
		return inDt;
	}

	public void setInDt(Date inDt) {
		this.inDt = inDt;
	}

	public Date getOutDt() {
		return outDt;
	}

	public void setOutDt(Date outDt) {
		this.outDt = outDt;
	}

	public Date getWaitStrDt() {
		return waitStrDt;
	}

	public void setWaitStrDt(Date waitStrDt) {
		this.waitStrDt = waitStrDt;
	}
	
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Car2) {
			return carNo.equals(((Car2)obj).getCarNo());
		}
		else return false;
	}
	
	public int hashCode() {
		return Objects.hash(carNo);
	}
	
}

class Cal {
	//주차시간
	public long GetTime(Car2 car2) {
		long time = car2.getOutDt().getTime() - car2.getInDt().getTime();
		long hour = time / (1000*60*60);
		return (int)hour;
	}
	
	public int feeCal(Car2 car2) {
		
		int fee = (int) (500 * (GetTime(car2) * 2));
		return fee;
	}
}

class Utils2{
	 public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(year, month-1, date, hour, minute, second);
		  cal.set(Calendar.MILLISECOND, 0);
		  return cal.getTime();
	 } 
		 
	 public static String getDate(Date date) {
		  SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		  return format1.format(date);
	 }
}

public class ParkingTest2 {


	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Utils2 u = new Utils2();
		Date start = new Date();
		Date end = new Date();
		ParkingLotAdm2 parking = new ParkingLotAdm2(start, end, 3);
		Car2 car = new Car2("가 1234", u.getDate(2018, 05, 30, 17, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 17, 00, 00));
		Car2 car2 = new Car2("나 1234", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 18, 00, 00));
		Car2 car3 = new Car2("다 1235", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
		Car2 car4 = new Car2("라 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 21, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
		Car2 car5 = new Car2("마 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 22, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));

		parking.addCar(car);
		parking.addCar(car);
		parking.addCar(car2);
		parking.addCar(car3);
		parking.addCar(car4);
		parking.addCar(car5);
		
		Comparator c = new comparing();
		parking.carSort(c);
		
//		System.out.println();
//		System.out.println(parking.selectCheck(1));
		
		System.out.println(parking.outCar(car) + " 원");
		System.out.println(parking.outCar(car2) + " 원");
		System.out.println(parking.outCar(car3) + " 원");
		System.out.println(parking.outCar(car4) + " 원");
		System.out.println(parking.outCar(car5) + " 원");

	}

}


