//package D0718;
//
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Objects;
//
//class Dog{
//   private String name;
//   private int age;
//   private String kind; //품종
//   private ArrayList inoculate = new ArrayList();//예방접종 기록
//   private HashSet handler = new HashSet();
//   private HashMap family = new HashMap();
//
//   Dog(String name, int age, String kind){
//      this.name = name;
//      this.age = age;
//      this.kind = kind;
//   }
//   
//   //예방접종을 하다
//   public void shotInoculate(String name, Date date) throws Exception {
//      Inoculate in = new Inoculate(name, date);
//      
//      //동일한 날짜에 같은 예방접종을 하면 안된다.(날짜 중복 x)
//      if(inoculate.contains(in)) {
//         throw new Exception("중복된 예방접종입니다.");
//      }
//
//      //품종별 예방접종 가능 기간이 아니면 오류 발생
//      Collections.sort(inoculate); //정렬
//      if(!inoculate.isEmpty()) {
//         Inoculate max = (Inoculate) inoculate.get(inoculate.size() - 1);//마지막 값
//         long day = 0;
//         if("셰퍼드".equals(this.kind)) {
//            day = diffofDate(max.getDate().toString(), date.toString());
//            if(day < 7) {
//               throw new Exception("예방접종 가능 기간이 아닙니다.");
//            }
//         }
//         else if("진도견".equals(this.kind)) {
//            day = diffofDate(max.getDate().toString(), date.toString());
//            if(day < 10) {
//               throw new Exception("예방접종 가능 기간이 아닙니다.");
//            }
//         }
//         else if("보더콜리".equals(this.kind)) {
//            day = diffofDate(max.getDate().toString(), date.toString());
//            if(day < 15) {
//               throw new Exception("예방접종 가능 기간이 아닙니다.");
//            }
//         }
//      }
//      inoculate.add(in);
//
//   }
//   
//   //예방접종 이력을 날짜별 정렬하여 출력한다.
//   //  형식: ‘{0} - {1}’, {0}: 날짜, {1}:예방접종명
//    //날자는 xxxx.xx.xx Format으로 출력한다
//   public void printInoculateHistory() {
//      //DataFormat format = DateFormat.getInstance(DateFormat.MEDIUM);
//      Iterator it = inoculate.iterator();
//
//      for(int i = 0; i < inoculate.size() ; i++)
//      {
//         if(it.hasNext()) {
//            //((Inoculate)it.next()).getDate().toString();
//            System.out.print(((Inoculate)it.next()).getDate() + "-");
//         }
//         
//         it = inoculate.iterator();
//         if(it.hasNext())
//         {
//            System.out.print(((Inoculate)it.next()).getName());
//         }
//         System.out.println();
//      }   
//   }
//
//   //Handler추가
//   //name/strDt/endDt가 동일한 값을 추가될 수 없다
//   public void addHandler(String name, String strDt, String endDt) throws Exception {
//      Handler h = new Handler(name, strDt, endDt);
//      System.out.println("111");
//      if(handler.contains(h)) {
//         throw new Exception("중복될 수 없습니다.");
//         
//      }
//      else 
//      {
//         handler.add(h);   
//      }
//   }
//   
//   //Handler의 총 훈련기간을 리턴한다. 
//   public int getTotalHandlerPeiod() {
//      int sumDay = 0;
//      Iterator it = handler.iterator();
//      
//      while(it.hasNext()) {
//         Handler h = (Handler)it.next();
//         
//         int sDay = Integer.parseInt(h.getStartDate());
//         int eDay = Integer.parseInt(h.getEndDate());
//         int calDay = eDay - sDay;
//         sumDay += calDay;
//         //System.out.println(sumDay);
//      }
//      
//      return sumDay;
//   }
//   
//   //가족을 추가한다
//   public void addFamily(String info) {
//      
//   }
//   
//   
//   public long diffofDate(String begin, String end) throws ParseException {
//      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//      
//      java.util.Date beginDate = formatter.parse(begin);
//      java.util.Date endDate = formatter.parse(end);
//      
//      long diff = endDate.getTime() - beginDate.getTime();
//      long diffDays = diff/(24*60*60*1000);
//      
//      return diffDays;
//   }
//
//   
//}
//
//class Handler{
//   String name;
//   String strDt;
//   String endDt;
//   
//   Handler(String name, String strDt, String endDt){
//      this.name = name;
//      this.strDt = strDt;
//      this.endDt = endDt;
//   }
//
//   public String getName() {
//      return name;
//   }
//   
//   public void setName(String name) {
//      this.name = name;
//   }
//   
//   public String getStartDate() {
//      return strDt;
//   }
//   
//   public void setStartDate(String strDt) {
//      this.strDt = strDt;
//   }
//   
//   public String getEndDate() {
//      return endDt;
//   }
//   
//   public void setEndDate(String endDt) {
//      this.endDt = endDt;
//   }
//   
//
//   public boolean equals(Object obj) {
//	   System.out.println("equals 호출");
//       String thisTmp = name + strDt + endDt;
//       String tmp = ((Handler) obj).getName() + ((Handler) obj).getStartDate() + ((Handler) obj).getEndDate();
//      if (obj != null && obj instanceof Handler) {
//         
//         boolean b = thisTmp.equals(tmp);
//         System.out.println("비교:" + b);
//         return b; // 같이 같은지 비교
//      } 
//      else return false;
//   }
//   
//   public int hashCode() {
//      return Objects.hash(name, strDt, endDt);
//   }
//   
//}
//
////예방접종
//class Inoculate implements Comparable{
//   String name;
//   Date date;
//   
//   Inoculate(String name, Date date){
//      this.name = name;
//      this.date = date;
//   }
//   
//   public String getName() {
//      return name;
//   }
//   
//   public void setName() {
//      this.name = name;
//   }
//   
//   public Date getDate() {
//      return date;
//   }
//   
//   public void setDate(Date date) {
//      this.date = date;
//   }
//   
//   public boolean equals(Object obj) {
//      if(obj != null && obj instanceof Inoculate) {
//         return name.equals(((Inoculate)obj).name);
//      }
//      else return false;
//   }
//
//   @Override
//   public int compareTo(Object obj) {
//      Inoculate in = (Inoculate)obj;
//      
//      return date.toString().compareTo(in.getDate().toString());
//   }
//
//}
//
//public class DogTest {
//
//   public static void main(String[] args) throws Exception {
//      Dog d = new Dog("캐빈", 10, "셰퍼드");
//      
//      try {
//         d.shotInoculate("장티프스", Date.valueOf("2019-07-01"));
//         d.shotInoculate("장티프스2", Date.valueOf("2019-08-03"));
//         d.printInoculateHistory();
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//      
//      
//      try {
//         d.addHandler("염민주", "20190701", "20190717");
//         d.addHandler("염민주", "20190701", "20190717");
//         d.addHandler("홍길동", "20190701", "20190717");
//         d.addHandler("김길동", "20190701", "20190717");
//         
//         System.out.println("Period:" + d.getTotalHandlerPeiod());
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//      
//   }
//
//}