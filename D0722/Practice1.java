package D0722;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Driver {
	private String name;
	private int licenceNo;
	private HashMap<String, Car> cars = new HashMap<String, Car>();
	private Accident[] history = new Accident[3];
	private int count = -1; //history count
	private int cnt = 0; //driverCar count
	private Car[] driverCar = new Car[10];
	
	Driver(String name, int licenceNo) {
		this.name = name;
		this.licenceNo = licenceNo;
	}
	
	//�����ڵ����� �߰��Ѵ�.
	public void addCar(Car car) {
		//������ȣ, ����, ��ⷮ
		//carNo�� key�� HashMap�� �߰��Ѵ�
        //������ �ڵ����� �߰��� �� ���� -> HashMap key
        //HashMap���� Key�� String, Value���� Car class�� �߰������ϴ� -> ���׸���(Generics)
		
		//Car car = new Car(car.carNo, car.type, car.volume);
		driverCar[cnt] = car;
		cnt++;
		cars.put(car.getCarNo(), car);
	}
	
	//�����ϰ� �ִ� Car�� ����Ѵ�.
	public void printCars() {
		//carNo/type/volume�� ����Ѵ�
		
		System.out.println();
		System.out.println("======== " + name + "�� ���� ���� ========");
		Set set = cars.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("�� ��ȣ : " + e.getKey() + " / " + e.getValue());
		}
	}
	
	//��� �̷��� �߰��Ѵ�.
	public void addAccident(Accident accident) {
		//����̷� �߰� �� Array size�� �Ѿ ��� Array size�� 2��� �÷��� �߰��Ѵ�
        //����̷� �߰� �� Car�� Driver�� �����ϰ� �ִ� Car�� �߰����� �ϴ�
		count++;
		//size ���� ���
		if(history.length <= count) {
			Accident[] temp = new Accident[history.length];
			temp = history;
			history = new Accident[history.length * 2];
			for(int i=0;i<history.length/2;i++) {
				history[i] = temp[i];
			}
		}
		
		//driverCar : driver�� car���� ���ϱ� ���� �����ϴ� �迭
		for(int i=0;i<cnt;i++) {
			if(accident.getCar().equals(driverCar[i])) {
				history[count] = accident;
				break;
			}
		}
		
	}
	
	public void printAccident() throws ParseException {
		SimpleDateFormat before = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat after = new SimpleDateFormat("yyyy-mm-dd");
		
		System.out.println();
		System.out.println("================ ��� ���� ================ ");
		for(int i=0;i<=count;i++) { //���� �߻� -> driver�� car�� �ƴѰ��� history()�� ���� x
			java.util.Date afterDate = before.parse(history[i].getDate()); 
			String afterFormat = after.format(afterDate);
			System.out.println("��ġ : " + history[i].getLocation() + " / ��¥ : " + afterFormat 
					+ " / �� ��ȣ : " + history[i].getCar().getCarNo() + " / ���� : "+ history[i].getCar().getType());
			
		}
	}
	
	public String toString() {
		return "�̸� : " + name + ", ������ ��ȣ : " + licenceNo;
	}
}

class Accident {
	private String location; //��ġ
	private String date; //��� ����
	private Car car; //��� ����
	
	Accident(String location, String date, Car car){
		this.location = location;
		this.date = date;
		this.car = car;
	}
	
	void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	void setCar(Car car) {
		this.car = car;
	}
	
	public Car getCar() {
		return car;
	}
}

class Car {
	private String carNo;
	private String type; 
	private int volume;
	
	Car(String carNo, String type, int volume){
		this.carNo = carNo;
		this.type = type;
		this.volume = volume;
	}
	
//	Car(String type, int volume){
//		this.type = type;
//		this.volume = volume;
//	}
	
	void setCarNo(String carNo){
		this.carNo = carNo;
	}
	
	public String getCarNo() {
		return carNo;
	}
	
	void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	void setVolume(int volume) {
		this.volume = volume;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public boolean equals(Object obj) {
		
		if(obj!=null && obj instanceof Car) {
			return carNo.equals(((Car)obj).getCarNo());
		}
		else return false;
	}
	
	public int hashCode() {
		return Objects.hash(carNo);
	}
	
	public String toString() {
		return "���� : " + getType() + " / ��ⷮ : " + volume;
	}
}

public class Practice1 {

	public static void main(String[] args) throws Exception {
		Driver driver = new Driver("ȫ�浿", 2018072200);
		System.out.println(driver);
		
		Car car = new Car("�� 1234", "VOLVE", 2000);
		Car car1 = new Car("�� 1541", "BMW", 1500);
		Car car2 = new Car("�� 9876", "VOLVE", 2000); //driver�� car�� �ƴ�.
		
//		driver.addCar("�� 1541", "BMW", 1500);
//		driver.addCar("�� 1234", "VOLVE", 2000);
//		driver.addCar("�� 1234", "VOLVE", 2000); //���� x (Hash ���� : �ߺ� x)
		
		driver.addCar(car);
		driver.addCar(car1);
		driver.addCar(car1); //���� x (Hash ���� : �ߺ� x)
		driver.printCars();

		Accident accident = new Accident("����", "20180914", car);
		Accident accident2 = new Accident("����", "20180914", car);
		Accident accident3 = new Accident("����", "20180914", car1);
		Accident accident4 = new Accident("����IC", "20180914", car1);
		Accident accident5 = new Accident("�д�", "20180914", car2); //driver�� car�� �ƴ�.
		try {
			driver.addAccident(accident);
			driver.addAccident(accident2);
			driver.addAccident(accident3);
			driver.addAccident(accident4);
			driver.addAccident(accident5); //���� ����
			driver.printAccident(); //���� �߻�
		} catch (Exception e) {
			e = new Exception("Car�� Driver�� �����ϰ� �ִ� Car�� �߰����� �մϴ�.");
			e.printStackTrace();
		}
	}

}
