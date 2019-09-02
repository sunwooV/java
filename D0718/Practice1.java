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
//	private String kind; //품종
//	private ArrayList inoculate; //예방접종
//	private HashSet handler; //훈련사
//	private HashMap family; //가족
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
//	//예방접종을 하다.
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void shotInoculate(String name, Date date) { 
//		Date LastKey = null;
////		Calendar c = Calendar.getInstance();
////		c.setTime(date); //Date->Calendar
//		
//		int Shepard = 7; //셰퍼드
// 		int Jindo = 10; //진도견
//		int Border = 15; //보더콜리
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
//					Exception e = new Exception("예방접종 가능 기간이 아닙니다.");
//					throw e; //예외를 발생시킴
//				} catch(Exception e) {
//					System.out.println("에러 메시지 : " + e.getMessage());
//					e.printStackTrace();
//				}
//			}
//			break;
//		case "Jindo":
//			if(CalDate(date,LastKey)<Jindo) {
//				try {
//					Exception e = new Exception("예방접종 가능 기간이 아닙니다.");
//					throw e; //예외를 발생시킴
//				} catch(Exception e) {
//					System.out.println("에러 메시지 : " + e.getMessage());
//					e.printStackTrace();
//				}
//			}
//			break;
//		case "Border":
//			if(CalDate(date,LastKey)<Border) {
//				try {
//					Exception e = new Exception("예방접종 가능 기간이 아닙니다.");
//					throw e; //예외를 발생시킴
//				} catch(Exception e) {
//					System.out.println("에러 메시지 : " + e.getMessage());
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
//	//날짜 차이를 계산한다.
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
//	//예방접종 이력을 날짜별 정렬하여 출력한다.
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
//    // 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
//    static public Date transformDate(String date)
//    {
//        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
//        
//        // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
//        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
//        
//        java.util.Date tempDate = null;
//        
//        try {
//            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
//            tempDate = beforeFormat.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        
//        // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
//        String transDate = afterFormat.format(tempDate);
//        
//        // 반환된 String 값을 Date로 변경한다.
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
//		d1.shotInoculate("예빵1", transformDate("20180101"));
//		d1.shotInoculate("예방2", transformDate("20180109"));
//
//	}
//
//}
