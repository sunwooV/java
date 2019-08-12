//package D0718;
//
//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//class Dog {
//	private String name;
//	private int age;
//	private String kind; //ǰ��
//	private ArrayList inoculate; //��������
//	private HashSet handler; //�Ʒû�
//	private HashMap family; //����
//	private Date date = new Date();
//	HashMap map = new HashMap();
//	
//	Dog(String name, int age, String kind){
//		setName(name);
//		setAge(age);
//		setKind(kind);
//		inoculate = new ArrayList(20);
//	}
//	
//	void setName(String name){
//		this.name = name;
//	}
//	
//	void setAge(int age) {
//		this.age = age;
//	}
//	
//	void setKind(String kind) {
//		this.kind = kind;
//	}
//	
//	String getName() {
//		return name;
//	}
//	
//	int getAge() {
//		return age;
//	}
//	
//	String getKind() {
//		return kind;
//	}
//	
//	//���������� �ϴ�.
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void shotInoculate(String name, Date date) { 
//		Date LastKey = null;
////		Calendar c = Calendar.getInstance();
////		c.setTime(date); //Date->Calendar
//		
//		int Shepard = 7; //���۵�
// 		int Jindo = 10; //������
//		int Border = 15; //�����ݸ�
//		
//		Set key = map.keySet();
//		
//		for(Iterator iterator = key.iterator();iterator.hasNext();) {
//			LastKey = (Date) iterator.next();
//		}
//		
//		map.put(date, name);
//		
//		inoculate.add(map);
//
//		
//		switch(getKind()) {
//		case "Shepard":
//			if(CalDate(date,LastKey)<Shepard) {
//				try {
//					Exception e = new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
//					throw e; //���ܸ� �߻���Ŵ
//				} catch(Exception e) {
//					System.out.println("���� �޽��� : " + e.getMessage());
//					e.printStackTrace();
//				}
//			}
//			break;
//		case "Jindo":
//			if(CalDate(date,LastKey)<Jindo) {
//				try {
//					Exception e = new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
//					throw e; //���ܸ� �߻���Ŵ
//				} catch(Exception e) {
//					System.out.println("���� �޽��� : " + e.getMessage());
//					e.printStackTrace();
//				}
//			}
//			break;
//		case "Border":
//			if(CalDate(date,LastKey)<Border) {
//				try {
//					Exception e = new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
//					throw e; //���ܸ� �߻���Ŵ
//				} catch(Exception e) {
//					System.out.println("���� �޽��� : " + e.getMessage());
//					e.printStackTrace();
//				}
//			}
//			break;
//		default:
//			System.out.println("hhh");
//			break;
//		}
//
//		
//	}
//	
//	//��¥ ���̸� ����Ѵ�.
//	public int CalDate(Date date, Date LastDate) {
//		
//		long calDate = LastDate.getTime() - date.getTime();
//		long calDateDays = calDate / (24*60*60*1000);
//			
//		calDateDays = Math.abs(calDateDays);
//		
//		return (int)calDateDays;
//		
//	}
//	
//	//�������� �̷��� ��¥�� �����Ͽ� ����Ѵ�.
//	public void printInoculateHistory() {
//		Iterator it = handler.iterator();
//		
//		
//		
//
//	}
//	
//}
//
//public class Practice1 {
//    // ��¥�� yyyymmdd �������� �ԷµǾ��� ��� Date�� �����ϴ� �޼���
//    static public Date transformDate(String date)
//    {
//        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
//        
//        // Date�� �����ϱ� ���ؼ��� ��¥ ������ yyyy-mm-dd�� �����ؾ� �Ѵ�.
//        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
//        
//        java.util.Date tempDate = null;
//        
//        try {
//            // ���� yyyymmdd�ε� ��¥ �������� java.util.Date��ü�� �����.
//            tempDate = beforeFormat.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        
//        // java.util.Date�� yyyy-mm-dd �������� �����Ͽ� String�� ��ȯ�Ѵ�.
//        String transDate = afterFormat.format(tempDate);
//        
//        // ��ȯ�� String ���� Date�� �����Ѵ�.
//        java.sql.Date d = java.sql.Date.valueOf(transDate);
//        
//        return d;
//    }
//
//
//	public static void main(String[] args) {
//
//		Dog d1 = new Dog("Jindo1", 10, "Jindo");
//		
//		d1.shotInoculate("����1", transformDate("20180101"));
//		d1.shotInoculate("����2", transformDate("20180109"));
//
//	}
//
//}
