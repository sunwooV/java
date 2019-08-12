package D0729;

import java.util.Date;
import java.util.HashMap;

class Company {
	private String name;
	private String location;
	private String industry;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}

class Dept {
	private String name;
	private int floor;
	private Company company;
	
	Dept(Company company, String name, int floor){
		this.company = company;
		this.name = name;
		this.floor = floor;
	}
	
	//사원 수를 출력하는 메소드
	public void printEmployeeCount(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
}

class Employee {
	private int emNo;
	private String name;
	private String eMail;
	private Date inDate; //입사일
	private Position position; //직급
	private HashMap employees; //사원
	
	Employee(int emNo, String name, String eMail, Date inDate, Position position){
		this.emNo =                 emNo;
		this.name = name;
		this.eMail = eMail;
		this.inDate = inDate;
		this.position = position;
		employees = new HashMap();
	}
	
	//입사
	public void addEmployee(Employee e, Dept d) {
		employees.put(e.getEmNo(), d);
	}
	
	//퇴사
	public void deleteEmployee(Employee e) {
		employees.remove(e.getEmNo());
	}
	
	//부서 이동
	public void changeDept(Employee e, Dept d) {
		employees.replace(e.getEmNo(), d);
	}
	
	//사원의 부서 출력
	public void printDept() {
		
	}
	
	//월급 출력 (직급 별 보너스 + 기본급)
	public void printMPay(Employee e) {

	}
	
	//사원별 연차 계산
	public void joinCompany(Employee e) {
		
	}

	public int getEmNo() {
		return emNo;
	}

	public void setEmNo(int emNo) {
		this.emNo = emNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public HashMap getEmployees() {
		return employees;
	}

	public void setEmployees(HashMap employees) {
		this.employees = employees;
	}
}

abstract class Position {

	//직급 별 보너스 계싼
	public abstract int Bonus();
	public abstract int vacation();                 
}

//부장
class Director extends Position {

	//부장의 bonus 반환
	@Override
	public int Bonus() {
		return 200000;
	}

	//휴가 일수 반환
	@Override
	public int vacation() {
		return 100;
	}
	
}

//과장
class Manager extends Position {

	//과장의 bonus 반환
	@Override
	public int Bonus() {
		return 100000;
	}
	
	//휴가 일수 반환
	@Override
	public int vacation() {
		return 80;
	}
	
}

public class Practice_0729 {

	public static void main(String[] args) {
		

	}

}
