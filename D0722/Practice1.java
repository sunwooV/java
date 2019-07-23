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
	
	//소유자동차를 추가한다.
	public void addCar(Car car) {
		//차량번호, 차종, 배기량
		//carNo를 key로 HashMap에 추가한다
        //동일한 자동차를 추가할 수 없다 -> HashMap key
        //HashMap에는 Key는 String, Value에는 Car class만 추가가능하다 -> 지네릭스(Generics)
		
		//Car car = new Car(car.carNo, car.type, car.volume);
		driverCar[cnt] = car;
		cnt++;
		cars.put(car.getCarNo(), car);
	}
	
	//소유하고 있는 Car를 출력한다.
	public void printCars() {
		//carNo/type/volume를 출력한다
		
		System.out.println();
		System.out.println("======== " + name + "의 소유 차들 ========");
		Set set = cars.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("차 번호 : " + e.getKey() + " / " + e.getValue());
		}
	}
	
	//사고 이력을 추가한다.
	public void addAccident(Accident accident) {
		//사고이력 추가 시 Array size를 넘어설 경우 Array size를 2배로 늘려서 추가한다
        //사고이력 추가 시 Car는 Driver가 소유하고 있는 Car만 추가가능 하다
		count++;
		//size 넘을 경우
		if(history.length <= count) {
			Accident[] temp = new Accident[history.length];
			temp = history;
			history = new Accident[history.length * 2];
			for(int i=0;i<history.length/2;i++) {
				history[i] = temp[i];
			}
		}
		
		//driverCar : driver의 car인지 비교하기 위해 저장하는 배열
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
		System.out.println("================ 사고 내역 ================ ");
		for(int i=0;i<=count;i++) { //예외 발생 -> driver의 car가 아닌것은 history()에 저장 x
			java.util.Date afterDate = before.parse(history[i].getDate()); 
			String afterFormat = after.format(afterDate);
			System.out.println("위치 : " + history[i].getLocation() + " / 날짜 : " + afterFormat 
					+ " / 차 번호 : " + history[i].getCar().getCarNo() + " / 차종 : "+ history[i].getCar().getType());
			
		}
	}
	
	public String toString() {
		return "이름 : " + name + ", 면허증 번호 : " + licenceNo;
	}
}

class Accident {
	private String location; //위치
	private String date; //사고 일자
	private Car car; //사고 차량
	
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
		return "차종 : " + getType() + " / 배기량 : " + volume;
	}
}

public class Practice1 {

	public static void main(String[] args) throws Exception {
		Driver driver = new Driver("홍길동", 2018072200);
		System.out.println(driver);
		
		Car car = new Car("나 1234", "VOLVE", 2000);
		Car car1 = new Car("라 1541", "BMW", 1500);
		Car car2 = new Car("라 9876", "VOLVE", 2000); //driver의 car가 아님.
		
//		driver.addCar("가 1541", "BMW", 1500);
//		driver.addCar("나 1234", "VOLVE", 2000);
//		driver.addCar("나 1234", "VOLVE", 2000); //저장 x (Hash 성격 : 중복 x)
		
		driver.addCar(car);
		driver.addCar(car1);
		driver.addCar(car1); //저장 x (Hash 성격 : 중복 x)
		driver.printCars();

		Accident accident = new Accident("강남", "20180914", car);
		Accident accident2 = new Accident("양재", "20180914", car);
		Accident accident3 = new Accident("강남", "20180914", car1);
		Accident accident4 = new Accident("양재IC", "20180914", car1);
		Accident accident5 = new Accident("분당", "20180914", car2); //driver의 car가 아님.
		try {
			driver.addAccident(accident);
			driver.addAccident(accident2);
			driver.addAccident(accident3);
			driver.addAccident(accident4);
			driver.addAccident(accident5); //예외 원인
			driver.printAccident(); //예외 발생
		} catch (Exception e) {
			e = new Exception("Car는 Driver가 소유하고 있는 Car만 추가가능 합니다.");
			e.printStackTrace();
		}
	}

}
