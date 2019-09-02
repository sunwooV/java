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
	
	//��� ���� ����ϴ� �޼ҵ�
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
	private Date inDate; //�Ի���
	private Position position; //����
	private HashMap employees; //���
	
	Employee(int emNo, String name, String eMail, Date inDate, Position position){
		this.emNo =                 emNo;
		this.name = name;
		this.eMail = eMail;
		this.inDate = inDate;
		this.position = position;
		employees = new HashMap();
	}
	
	//�Ի�
	public void addEmployee(Employee e, Dept d) {
		employees.put(e.getEmNo(), d);
	}
	
	//���
	public void deleteEmployee(Employee e) {
		employees.remove(e.getEmNo());
	}
	
	//�μ� �̵�
	public void changeDept(Employee e, Dept d) {
		employees.replace(e.getEmNo(), d);
	}
	
	//����� �μ� ���
	public void printDept() {
		
	}
	
	//���� ��� (���� �� ���ʽ� + �⺻��)
	public void printMPay(Employee e) {

	}
	
	//����� ���� ���
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

	//���� �� ���ʽ� ���
	public abstract int Bonus();
	public abstract int vacation();                 
}

//����
class Director extends Position {

	//������ bonus ��ȯ
	@Override
	public int Bonus() {
		return 200000;
	}

	//�ް� �ϼ� ��ȯ
	@Override
	public int vacation() {
		return 100;
	}
	
}

//����
class Manager extends Position {

	//������ bonus ��ȯ
	@Override
	public int Bonus() {
		return 100000;
	}
	
	//�ް� �ϼ� ��ȯ
	@Override
	public int vacation() {
		return 80;
	}
	
}

public class Practice_0729 {

	public static void main(String[] args) {
		

	}

}
