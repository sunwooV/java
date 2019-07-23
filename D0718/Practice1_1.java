package D0718;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Dog2 {
	private String name;
	private int age;
	private String kind; // ǰ��
	private ArrayList inoculate = new ArrayList(); // ��������
	private HashSet handler = new HashSet(); // �Ʒû�
	private HashMap family = new HashMap(); // ����

	@SuppressWarnings("rawtypes")
	Dog2(String name, int age, String kind){
			this.name = name;
			this.age = age;
			this.kind = kind;
	}

	// ���������� �ϴ�.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void shotInoculate(String name, Date date) throws Exception {
		Inoculate in = new Inoculate(name, date);

		//���� ������ �ִ��� Ȯ��
		if(inoculate.contains(in)) {
			throw new Exception("�ߺ��� ���������Դϴ�.");
		}
		
		//ǰ���� �������� ���� �Ⱓ�� �ƴ��� Ȯ��
		Collections.sort(inoculate);

		if(!inoculate.isEmpty()) { //inoculate arrayList�� ������� ������
			Inoculate max = (Inoculate) inoculate.get(inoculate.size() - 1);
			long day = 0;
			if("���۵�".equals(this.kind)) {
				day = diffofDate(max.getDate().toString(), date.toString());
				if(day<7) {
					throw new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
				}
			}
			else if("������".equals(this.kind)) {
				day = diffofDate(max.getDate().toString(), date.toString());
				if(day<10) {
					throw new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
				}
			}
			else if("�����ݸ�".equals(this.kind)) {
				day = diffofDate(max.getDate().toString(), date.toString());
				if(day<15) {
					throw new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
				}
			}
			
			inoculate.add(in);
		}
		else { //inoculate arrayList�� ������� �� - ó�� �Է��ϴ� �ܰ�
			inoculate.add(in); 
		}

	}
	
	public void printInoculateHistory() {
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Iterator it = inoculate.iterator();
		Iterator it2 = inoculate.iterator();
		while(it.hasNext() && it2.hasNext()) {
			System.out.println("{" + format.format(((Inoculate)it2.next()).getDate()) + "} - {" + ((Inoculate)it.next()).getName() + "}");
		}
	}

	private long diffofDate(String begin, String end) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date beginDate = format.parse(begin);
		java.util.Date endDate = format.parse(end);
		
		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24*60*60*1000);
		
		return diffDays;
	}
	
	public void addHandler(String name, String strDt, String endDt) throws Exception {
		Handler h = new Handler(name, strDt, endDt);
		
		if(handler.contains(h)) {
			throw new Exception("�ߺ��� �� �����ϴ�.");
		}
		else {
			handler.add(h);
		}
	}
	
	public int getTotalHandlerPeriod() throws Exception {
		Iterator it = handler.iterator();
		
		int sum = 0;
		
		while(it.hasNext()) {
			Handler h = (Handler)it.next();
			
			String strDt = h.getStrDt();
			String endDt = h.getEndDt();
			long lo = diffofDate(strDt, endDt);
			
			sum += lo;
		}
		
		return sum;
		
	}
	
	//���� �߰�
	@SuppressWarnings("unchecked")
	public void addFamily(String info) throws Exception {
		String[] array = info.split("/");
		String type = array[0];
		String name = array[1];
		
		if(family.containsKey(name)) {
			throw new Exception("�̸��� �ߺ��� �� �����ϴ�.");
		}
		else {
			Family fm = new Family(name, type);
		
			family.put(fm.getName(), fm.getType());
		}
		
	}
	
	public void printFamily() {
		Set set = family.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("�̸� : " + e.getKey() + ", Ÿ�� : " + e.getValue());
		}
	}
}

class Inoculate implements Comparable{
	String name;
	Date date;
	
	Inoculate(String name, Date date){
		this.name = name;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}
	
	void setDate(Date date) {
		this.date = date;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Inoculate) {
			return name.equals(((Inoculate)obj).name);
		}
		else return false;
	}

	@Override
	public int compareTo(Object obj) {
		Inoculate param = (Inoculate) obj;
		
		return date.toString().compareTo(param.getDate().toString());
	}

	
}

class Handler {
	String name;
	String strDt;
	String endDt;
	
	Handler(String name, String strDt, String endDt){
		this.name = name;
		this.strDt = strDt;
		this.endDt = endDt;
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	public String getStrDt() {
		return strDt;
	}
	
	void setStrDt(String strDt) {
		this.strDt = strDt;
	}
	
	public String getEndDt() {
		return endDt;
	}
	
	void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	
	public boolean equals(Object obj) {
		String thisTemp = name + strDt + endDt;
		String tmp = ((Handler)obj).getName() + ((Handler)obj).getStrDt() + ((Handler)obj).getEndDt();
		
		if(obj!=null && obj instanceof Handler) {
			return thisTemp.equals(tmp);
		}
		else
			return false;
	}
	
	
	public int hashCode() {
		return Objects.hash(name, strDt, endDt);
	}
	
	public String toString() {
		return "name : " + name + ", startDate : " + strDt + ", endDate : " + endDt;
	}  
	
}

class Family {
	String type;
	String name;
	
	Family(String name, String type){
		this.type = type;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	void setType(String type) {
		this.type = type;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Family) {
			return name.equals(((Family)obj).name);
		}
		else return false;
	}
	
	public int hashCode() {
		return Objects.hash(name, type);
	}
	
}


public class Practice1_1 {

	public static void main(String[] args) throws Exception {
		Dog2 d1 = new Dog2("�۸�", 12, "���۵�");
		
		try {
			d1.shotInoculate("����1", Date.valueOf("2018-01-01"));
			d1.shotInoculate("����2", Date.valueOf("2018-01-08"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("=== ���� ���� history ===");
		d1.printInoculateHistory();
		
		System.out.println();
		System.out.println("==== handler �߰� ====");
		d1.addHandler("ȫ�浿", "2019-01-05", "2019-02-01");
		d1.addHandler("��浿", "2019-01-05", "2019-02-01");
//		d1.addHandler("��浿", "2019-01-05", "2019-02-01"); //���ܹ߻�
		
		try {
			System.out.println("�� handler period : " + d1.getTotalHandlerPeriod() + "��");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("==== Family �߰� ====");
		d1.addFamily("�ΰ�/����");
		d1.addFamily("���/����");
		d1.addFamily("�ڸ�/����");
//		d1.addFamily("���/����");//���ܹ߻�
		d1.printFamily();
	}

}
