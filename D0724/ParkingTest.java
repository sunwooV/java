////1. ParkingLotAdm (���������) 
////
////- �Ӽ�: private Date startDt, private Date endDt, private final int maxCarsu
////
////         private (?) cars, private (?) outCars, private (?) waitCars
////
////- ������:
////
////ParkingLotAdm (Date statDt, Date endDt, int maxCarsu)
////
////- �޼ҵ�
////
////public void addCar(Car car): �����ϴ� ���� �߰��Ѵ�
////
////public int outCar(Car car): ������ ���� �����ϸ� outCars�� �߰��ϰ� ��������� �����Ѵ�
////
////                          ��������� 30�д� 500�� �̴�
////
////                          ����������� ���� ������ ������Ų��(����������� ���� �������� ���� ����)
////
////public void carSort(Comparator c): �Ķ���ͷ� ������ ���ı��ؿ� ���� ���������� Sort�Ѵ�
////
////public Collection selectCheck(int hour): hour�ð� �̻� �������� ���� �����Ѵ�
////
////public int getTotalFee(): �� ���������� ���� �����Ѵ�
////
////
////
////2. Car
////
////- �Ӽ�: private String carNo, private Date inDt(�����ð�), private Date outDt(�����ð�), private int fee
////
////        private Date waitStrDt(�����۽ð�)
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
////������ ����
//class ParkingLotAdm {
//	private Date startDt;
//	private Date endDt;
//	private final int maxCarsu;
//	private Car[] cars;
//	private Car[] outCars;
//	private Queue waitCars = new LinkedList();
//	private ArrayList oldCars = new ArrayList();
//	private int count = 0; //������ ���� ��
//	private int count2 = 0; //�ƿ��� ���� ��
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
//	//�����ϴ� ���� �߰��Ѵ�.
//	public void addCar(Car car) {
//		
//		//������ �� ���� �ʾ��� ��
//		if(count < maxCarsu) {
//			cars[count] = car;
//			System.out.println("[" + u.getDate(car.getInDt()) + "] " + cars[count].getCarNo() + " ���� �����忡 ���ϴ�.");
//			count++;
//		}
//		//�������� �� á�� ��
//		else {
//			waitCars.offer(car);
//			System.out.println("[" + u.getDate(car.getInDt()) + "] " + car.getCarNo() + " ���� ����մϴ�.");
//		}
//		
//	}
//	
//	//������ ���� �����ϸ� outCars�� �߰��ϰ� ��������� �����Ѵ�.
//	//���ĺ���� 30�д� 500���̴�.
//	//����������� ���� ������ ������Ų��(����������� ���� �������� ���� ����)
//	public int outCar(Car car) {
//
//		//������ ���� ������ �迭���� ������ ���� �迭 ����
//		for(int i=0;i<count;i++) {
//			if(cars[i].equals(car)) {
//				outCars[count2] = car;
//				System.out.println();
//				System.out.println("[" + u.getDate(car.getOutDt()) + "] " + outCars[count2].getCarNo() + " ���� �������� �����ϴ�.");
//				count2++;
//				//�������� �߰��� ���� ���� ��
//				if(i+1 == count) {
//					cars[i] = null;
//					break;
//				}
//				//�߰��� �߰��� ���� ���� ��
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
//		//��ٸ��� ���� ������
//		if(!waitCars.isEmpty()) {
//			cars[count] = (Car) waitCars.poll();
//			cars[count].setInDt(car.getOutDt());
//			System.out.println();
//			System.out.println("[" + u.getDate(cars[count].getInDt()) + "] " + cars[count].getCarNo() + " ���� ��⸦ ������ �����忡 ���ϴ�.");
//			count++;
//		}
//		
//		//������� ����
//		int fee = car.feeCal();
//		
//		return fee;
//	}
//	
//	//�Ķ���ͷ� ������ ���ı��ؿ� ���� ���������� Sort�Ѵ�.
//	public void carSort(Comparator c) {
//		
//	}
//	
//	//hour�ð� �̻� �������� ���� �����Ѵ�.
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
//	//�� ���������� ���� �����Ѵ�.
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
//	private Date inDt; //�����ð�
//	private Date outDt; //�����ð�
//	private int fee; //���
//	private Date waitStrDt; //��� ���� �ð�
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
//	//�����ð�
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
//		Car car = new Car("�� 1234", u.getDate(2018, 05, 30, 17, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 17, 00, 00));
//		Car car2 = new Car("�� 1234", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 18, 00, 00));
//		Car car3 = new Car("�� 1235", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
//		Car car4 = new Car("�� 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 21, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
//		Car car5 = new Car("�� 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 22, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
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
//		System.out.println(parking.outCar(car) + " ��");
//		System.out.println(parking.outCar(car2) + " ��");
//		System.out.println(parking.outCar(car3) + " ��");
//		System.out.println(parking.outCar(car4) + " ��");
//		System.out.println(parking.outCar(car5) + " ��");
//
//	}
//
//}
//
//
