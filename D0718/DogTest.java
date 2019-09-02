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
//   private String kind; //ǰ��
//   private ArrayList inoculate = new ArrayList();//�������� ���
//   private HashSet handler = new HashSet();
//   private HashMap family = new HashMap();
//
//   Dog(String name, int age, String kind){
//      this.name = name;
//      this.age = age;
//      this.kind = kind;
//   }
//   
//   //���������� �ϴ�
//   public void shotInoculate(String name, Date date) throws Exception {
//      Inoculate in = new Inoculate(name, date);
//      
//      //������ ��¥�� ���� ���������� �ϸ� �ȵȴ�.(��¥ �ߺ� x)
//      if(inoculate.contains(in)) {
//         throw new Exception("�ߺ��� ���������Դϴ�.");
//      }
//
//      //ǰ���� �������� ���� �Ⱓ�� �ƴϸ� ���� �߻�
//      Collections.sort(inoculate); //����
//      if(!inoculate.isEmpty()) {
//         Inoculate max = (Inoculate) inoculate.get(inoculate.size() - 1);//������ ��
//         long day = 0;
//         if("���۵�".equals(this.kind)) {
//            day = diffofDate(max.getDate().toString(), date.toString());
//            if(day < 7) {
//               throw new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
//            }
//         }
//         else if("������".equals(this.kind)) {
//            day = diffofDate(max.getDate().toString(), date.toString());
//            if(day < 10) {
//               throw new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
//            }
//         }
//         else if("�����ݸ�".equals(this.kind)) {
//            day = diffofDate(max.getDate().toString(), date.toString());
//            if(day < 15) {
//               throw new Exception("�������� ���� �Ⱓ�� �ƴմϴ�.");
//            }
//         }
//      }
//      inoculate.add(in);
//
//   }
//   
//   //�������� �̷��� ��¥�� �����Ͽ� ����Ѵ�.
//   //  ����: ��{0} - {1}��, {0}: ��¥, {1}:����������
//    //���ڴ� xxxx.xx.xx Format���� ����Ѵ�
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
//   //Handler�߰�
//   //name/strDt/endDt�� ������ ���� �߰��� �� ����
//   public void addHandler(String name, String strDt, String endDt) throws Exception {
//      Handler h = new Handler(name, strDt, endDt);
//      System.out.println("111");
//      if(handler.contains(h)) {
//         throw new Exception("�ߺ��� �� �����ϴ�.");
//         
//      }
//      else 
//      {
//         handler.add(h);   
//      }
//   }
//   
//   //Handler�� �� �ƷñⰣ�� �����Ѵ�. 
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
//   //������ �߰��Ѵ�
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
//	   System.out.println("equals ȣ��");
//       String thisTmp = name + strDt + endDt;
//       String tmp = ((Handler) obj).getName() + ((Handler) obj).getStartDate() + ((Handler) obj).getEndDate();
//      if (obj != null && obj instanceof Handler) {
//         
//         boolean b = thisTmp.equals(tmp);
//         System.out.println("��:" + b);
//         return b; // ���� ������ ��
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
////��������
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
//      Dog d = new Dog("ĳ��", 10, "���۵�");
//      
//      try {
//         d.shotInoculate("��Ƽ����", Date.valueOf("2019-07-01"));
//         d.shotInoculate("��Ƽ����2", Date.valueOf("2019-08-03"));
//         d.printInoculateHistory();
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//      
//      
//      try {
//         d.addHandler("������", "20190701", "20190717");
//         d.addHandler("������", "20190701", "20190717");
//         d.addHandler("ȫ�浿", "20190701", "20190717");
//         d.addHandler("��浿", "20190701", "20190717");
//         
//         System.out.println("Period:" + d.getTotalHandlerPeiod());
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//      
//   }
//
//}