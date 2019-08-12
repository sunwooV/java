package D0805;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Company{
	private String name;
	private String location;
	private String business;
	private HashMap posts = new HashMap();
	
	Company(String name, String location, String business){
		this.name = name;
		this.location = location;
		this.business = business;
	}
	
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

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	
	//ä����� �߰�
	public void addPost(AnnounceJob job, AnnounceType type) {
		posts.put(job, type);
	}
	
	//ä����� ���� 
	public void deletePost(AnnounceJob job) {
		posts.remove(job);
	}
	
	//ä����� ���
	public void printPost() {
		Set set = posts.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println();//���� ���
		}
	}
	
}

class AnnounceJob{
	private Date startDt;
	private Date endDt;
	private String part;//���� �μ�
	private int num; //���� �ο�
	
	AnnounceJob(Date startDt, Date endDt, String part){
		this.startDt = startDt;
		this.endDt = endDt;
		this.part = part;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}

class AnnounceType{
	HashSet newMember = new HashSet();
	HashSet oldMember = new HashSet();
   	public void checkCareer(Volunteer v) {
   		if(v.getCareer() < 12) {
   			newMember.add(v);
   		}
   		else {
   			oldMember.add(v);
   		}
   		
   	}
}

interface PF{
	//�հݿ��� ����ϴ� �޼ҵ�
	abstract void resultPF();
}

class Volunteer implements PF{
	private String name;
	private String tel;
	private String eMail;
	private String gender;
	private int career;
	
	public Volunteer(String name, String tel, String eMail, String gender, int career) {
		this.name = name;
		this.tel = tel;
		this.eMail = eMail;
		this.gender = gender;
		this.career = career;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	//�����ϴ�.
	public void apply() {
		System.out.println(name + "���� �����߽��ϴ�.");
	}
	
	//������ ����ϴ�.
	public void cancelApply() {
		
	}

	//�����ں��� �հ� ���� �Ǻ� ���
	@Override
	public void resultPF() {
		
	}
	
}

public class ApplyTest {

	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//		Company com = new Company("ȸ��", "����", "IT");
//		AnnounceJob aj = new AnnounceJob(sdf.parse("2018-09-06"), sdf.parse("2019-06-02"), "����Ƽ");
//		AnnounceType at = new NewMember();
//		
//		com.addPost(aj, at);
//		com.printPost();
//		
//		Volunteer v = new Volunteer("�̸�", "010-2222-2222", "lsdjkf@ndfdf.ciom", "����", 13);
//		v.checkCareer();
	}

}
