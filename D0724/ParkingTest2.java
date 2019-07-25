//1. ParkingLotAdm (���������) 
//
//- �Ӽ�: private Date startDt, private Date endDt, private final int maxCarsu
//
//         private (?) cars, private (?) outCars, private (?) waitCars
//
//- ������:
//
//ParkingLotAdm (Date statDt, Date endDt, int maxCarsu)
//
//- �޼ҵ�
//
//public void addCar(Car car): �����ϴ� ���� �߰��Ѵ�
//
//public int outCar(Car car): ������ ���� �����ϸ� outCars�� �߰��ϰ� ��������� �����Ѵ�
//
//                          ��������� 30�д� 500�� �̴�
//
//                          ����������� ���� ������ ������Ų��(����������� ���� �������� ���� ����)
//
//public void carSort(Comparator c): �Ķ���ͷ� ������ ���ı��ؿ� ���� ���������� Sort�Ѵ�
//
//public Collection selectCheck(int hour): hour�ð� �̻� �������� ���� �����Ѵ�
//
//public int getTotalFee(): �� ���������� ���� �����Ѵ�
//
//
//
//2. Car
//
//- �Ӽ�: private String carNo, private Date inDt(�����ð�), private Date outDt(�����ð�), private int fee
//
//        private Date waitStrDt(�����۽ð�)
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

//������ ����
class ParkingLotAdm2 {
	private Date startDt;
	private Date endDt;
	private final int maxCarsu;
	private HashMap cars;
	private HashMap outCars;
	private Queue waitCars = new LinkedList();
	private ArrayList oldCars = new ArrayList();
	private int count = 0; //������ ���� ��
	private int count2 = 0; //�ƿ��� ���� ��
	Utils2 u = new Utils2();
	Cal c = new Cal();
	
	ParkingLotAdm2(Date startDt, Date endDt, int maxCarsu){
		this.startDt = startDt;
		this.endDt = endDt;
		this.maxCarsu = maxCarsu;
		cars = new HashMap();
		outCars = new HashMap();
	}
	
	//�����ϴ� ���� �߰��Ѵ�.
	public void addCar(Car2 car2) {
		
		//������ �� ���� �ʾ��� ��
		if(count < maxCarsu) {
			String value = car2.getInDt() + "" + car2.getOutDt();
			cars.put(car2.getCarNo(), value);
			System.out.println("[" + u.getDate(car2.getInDt()) + "] " + car2.getCarNo() + " ���� �����忡 ���ϴ�.");
			count++;
		}
		
		//�������� �� á�� ��
		else {
			waitCars.offer(car2);
			System.out.println("[" + u.getDate(car2.getInDt()) + "] " + car2.getCarNo() + " ���� ����մϴ�.");
		}
		
	}
	
	//������ ���� �����ϸ� outCars�� �߰��ϰ� ��������� �����Ѵ�.
	//���ĺ���� 30�д� 500���̴�.
	//����������� ���� ������ ������Ų��(����������� ���� �������� ���� ����)
	public int outCar(Car2 car2) {

		if(cars.containsKey(car2.getCarNo())) {
			String value = car2.getInDt() + "" + car2.getOutDt();
			outCars.put(car2.getCarNo(), value);
			System.out.println();
			System.out.println("[" + u.getDate(car2.getOutDt()) + "] " + car2.getCarNo() + " ���� �������� �����ϴ�.");
			count2++;
				
			cars.remove(car2.getCarNo());

		}
		
		//��ٸ��� ���� ������
		if(!waitCars.isEmpty()) {
			Object p = waitCars.poll();
			String value = car2.getOutDt() + "" + ((Car2)p).getOutDt();
			cars.put(((Car2)p).getCarNo(), value);
			
			
			System.out.println();
			System.out.println("[" + u.getDate(car2.getOutDt()) + "] " + ((Car2)p).getCarNo() + " ���� ��⸦ ������ �����忡 ���ϴ�.");
			count++;
		}
		
		//������� ����
		int fee = c.feeCal(car2);
		
		return fee;
	}
	
	//�Ķ���ͷ� ������ ���ı��ؿ� ���� ���������� Sort�Ѵ�.
	public void carSort(Comparator c) {
		List list = new ArrayList();
        list.addAll(cars.keySet());
         
        Collections.sort(list, c);
        
        for(int i=0;i<list.size();i++) {
        	System.out.println(list.get(i));
        }
	}
	
//	//hour�ð� �̻� �������� ���� �����Ѵ�.
//	public Collection selectCheck(int hour) {
//		for(int i=0;i<count;i++) {
//			if(cars[i].GetTime() >= hour) {
//				oldCars.add(cars[i]);
//			}
//		}
//		return oldCars;
//	}
	
	
//	//�� ���������� ���� �����Ѵ�.
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
	private Date inDt; //�����ð�
	private Date outDt; //�����ð�
	private int fee; //���
	private Date waitStrDt; //��� ���� �ð�
	
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
	//�����ð�
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
		Car2 car = new Car2("�� 1234", u.getDate(2018, 05, 30, 17, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 17, 00, 00));
		Car2 car2 = new Car2("�� 1234", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 18, 00, 00));
		Car2 car3 = new Car2("�� 1235", u.getDate(2018, 05, 30, 18, 00, 00), u.getDate(2018, 05, 30, 20, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
		Car2 car4 = new Car2("�� 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 21, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));
		Car2 car5 = new Car2("�� 1235", u.getDate(2018, 05, 30, 19, 00, 00), u.getDate(2018, 05, 30, 22, 00, 00), u.getDate(2018, 05, 30, 19, 00, 00));

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
		
		System.out.println(parking.outCar(car) + " ��");
		System.out.println(parking.outCar(car2) + " ��");
		System.out.println(parking.outCar(car3) + " ��");
		System.out.println(parking.outCar(car4) + " ��");
		System.out.println(parking.outCar(car5) + " ��");

	}

}


