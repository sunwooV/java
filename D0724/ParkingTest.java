////1. ParkingLotAdm (주차장관리) 
////
////- 속성: private Date startDt, private Date endDt, private final int maxCarsu
////
////         private (?) cars, private (?) outCars, private (?) waitCars
////
////- 생성자:
////
////ParkingLotAdm (Date statDt, Date endDt, int maxCarsu)
////
////- 메소드
////
////public void addCar(Car car): 주차하는 차를 추가한다
////
////public int outCar(Car car): 나가는 차를 제거하면 outCars에 추가하고 주차비용을 리턴한다
////
////                          주차비용은 30분당 500원 이다
////
////                          주차대기중인 차가 있으면 주차시킨다(주차대기중인 차는 대기순서에 따라 주차)
////
////public void carSort(Comparator c): 파라미터로 전달한 정렬기준에 따라 주차차량을 Sort한다
////
////public Collection selectCheck(int hour): hour시간 이상 주차중인 차를 리턴한다
////
////public int getTotalFee(): 총 주차수수료 합을 리턴한다
////
////
////
////2. Car
////
////- 속성: private String carNo, private Date inDt(입차시간), private Date outDt(출차시간), private int fee
////
////        private Date waitStrDt(대기시작시간)
////
//package D0724;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.Queue;
//
////주차장 관리
//class ParkingLotAdm {
//	private Date startDt;
//	private Date endDt;
//	private final int maxCarsu;
//	private Car[] cars;
//	private Car[] outCars;
//	private Queue waitCars = new LinkedList();
//	private ArrayList oldCars = new ArrayList();
//	private int count = 0; //주차된 차량 수
//	private int count2 = 0; //아웃된 차량 수
//	Utils u = new Utils();
//	
//	ParkingLotAdm(Date startDt, Date endDt, int maxCarsu){
//		this.startDt = startDt;
//		this.endDt = endDt;
//		this.maxCarsu = maxCarsu;
//		cars = new Car[maxCarsu];
//		outCars = new Car[10];
//	}
//	
//	//주차하는 차를 추가한다.
//	public void addCar(Car car) {
//		
//		//주차장 꽉 차지 않았을 때
//		if(count < maxCarsu) {
//			cars[count] = car;
//			System.out.println("[" + u.getDate(car.getInDt()) + "] " + cars[count].getCarNo() + " 차가 주차장에 들어갑니다.");
//			count++;
//		}
//		//주차장이 꽉 찼을 때
//		else {
//			waitCars.offer(car);
//			System.out.println("[" + u.getDate(car.getInDt()) + "] " + car.getCarNo() + " 차가 대기합니다.");
//		}
//		
//	}
//	
//	//나가는 차를 제거하면 outCars에 추가하고 주차비용을 리턴한다.
//	//주파비용은 30분당 500원이다.
//	//주차대기중인 차가 있으면 주차시킨다(주차대기중인 차는 대기순서에 따라 주차)
//	public int outCar(Car car) {
//
//		//주차된 차를 저장한 배열에서 나가는 차량 배열 삭제
//		for(int i=0;i<count;i++) {
//			if(cars[i].equals(car)) {
//				outCars[count2] = car;
//				System.out.println();
//				System.out.println("[" + u.getDate(car.getOutDt()) + "] " + outCars[count2].getCarNo() + " 차가 주차장을 나갑니다.");
//				count2++;
//				//마지막에 추가된 차가 빠질 때
//				if(i+1 == count) {
//					cars[i] = null;
//					break;
//				}
//				//중간에 추가된 차가 빠질 때
//				else{
//					while(i+1 < count) {
//				
//					Car temp = cars[i+1];
//					cars[i] = temp;
//					cars[i+1] = null;
//					i++;
//					}
//				}
//
//			}
//		}
//		count--;
//		
//		//기다리는 차가 있으면
//		if(!waitCars.isEmpty()) {
//			cars[count] = (Car) waitCars.poll();
//			cars[count].setInDt(car.getOutDt());
//			System.out.println();
//			System.out.println("[" + u.getDate(cars[count].getInDt()) + "] " + cars[count].getCarNo() + " 차가 대기를 끝내고 주차장에 들어갑니다.");
//			count++;
//		}
//		
//		//주차비용 리턴
//		int fee = car.feeCal();
//		
//		return fee;
//	}
//	
//	//파라미터로 전달한 정렬기준에 따라 주차차량을 Sort한다.
//	public void carSort(Comparator c) {
//		
//	}
//	
//	//hour시간 이상 주차중인 차를 리턴한다.
//	public Collection selectCheck(int hour) {
//		for(int i=0;i<count;i++) {
//			if(cars[i].GetTime() >= hour) {
//				oldCars.add(cars[i]);
//			}
//		}
//		return oldCars;
//	}
//	
//	
//	//총 주차수수료 합을 리턴한다.
//	public int getTotalFee() {
//		int total = 0;
//		for(int i=0;i<outCars.length;i++) {
//			total += (int) outCars[i].GetTime();
//		}
//		return total;
//		
//	}
//}
//
//class Car {
//	private String carNo;
//	private Date inDt; //입차시간
//	private Date outDt; //출차시간
//	private int fee; //요금
//	private Date waitStrDt; //대기 시작 시간
//	
//	Car(){}
//	
//	Car(String carNo){
//		this.carNo = carNo;
//	}
//	
//	Car(String carNo, Date inDt, Date outDt, Date waitStrDt){
//		this.carNo = carNo;
//		this.inDt = inDt;
//		this.outDt = outDt;
//		this.waitStrDt = waitStrDt;
//	}
//
//	public String getCarNo() {
//		return carNo;
//	}
//
//	public void setCarNo(String carNo) {
//		this.carNo = carNo;
//	}
//
//	public Date getInDt() {
//		return inDt;
//	}
//
//	public void setInDt(Date inDt) {
//		this.inDt = inDt;
//	}
//
//	public Date getOutDt() {
//		return outDt;
//	}
//
//	public void setOutDt(Date outDt) {
//		this.outDt = outDt;
//	}
//
//	public Date getWaitStrDt() {
//		return waitStrDt;
//	}
//
//	public void setWaitStrDt(Date waitStrDt) {
//		this.waitStrDt = waitStrDt;
//	}
//	
//	//주차시간
//	public long GetTime() {
//		long time = getOutDt().getTime() - getInDt().getTime();
//		long hour = time / (1000*60*60);
//		return (int)hour;
//	}
//	
//	public int feeCal() {
//		
//		fee = (int) (500 * (GetTime() * 2));
//		return fee;
//	}
//	
//}
//
//class Utils{
//	 public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
//		  Calendar cal = Calendar.getInstance();
//		  cal.set(year, month-1, date, hour, minute, second);
//		  cal.set(Calendar.MILLISECOND, 0);
//		  return cal.getTime();
//	 } 
//		 
//	 public static String getDate(Date date) {
//		  SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
//		  return format1.format(date);
//	 }
//}
//
//public class ParkingTest {
//
//
//	@SuppressWarnings("static-access")
//	public static void main(String[] args) {
//		Utils u = new Utils();
//		Date start = new Date();
//		Date end = new Date();
//		ParkingLotAdm parking = new ParkingLotAdm(start, end, 3);
//		Car car = new Car("가 1234", u.getDate(2018, 05, 30, 17, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 17, 00, 00));
//		Car car2 = new Car("나 1234", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 18, 00, 00));
//		Car car3 = new Car("다 1235", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
//		Car car4 = new Car("라 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 21, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
//		Car car5 = new Car("마 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 22, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
//
//		parking.addCar(car);
//		parking.addCar(car2);
//		parking.addCar(car3);
//		parking.addCar(car4);
//		parking.addCar(car5);
//		
////		System.out.println();
////		System.out.println(parking.selectCheck(1));
//		
//		System.out.println(parking.outCar(car) + " 원");
//		System.out.println(parking.outCar(car2) + " 원");
//		System.out.println(parking.outCar(car3) + " 원");
//		System.out.println(parking.outCar(car4) + " 원");
//		System.out.println(parking.outCar(car5) + " 원");
//
//	}
//
//}
//
//
