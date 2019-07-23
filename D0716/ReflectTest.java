package D0716;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
	public String str;
	
	public ReflectTest(String str) {
		this.str = str;
		System.out.println("REFLECT 생성자 호출: " + str);
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = Class.forName("day6.ReflectTest");
		System.out.println(c);
		
		Constructor[] ctor = c.getConstructors(); //생성자를 얻는다.
		for(int i=0;i<ctor.length;i++) {
			System.out.println("생성자: "+ctor[i].toString());
		}
		
		Method[] m = c.getMethods(); //메소드를 얻는다.
		for(int i=0;i<m.length;i++) {
			System.out.println("메소드: " +m[i].toString());
		}
		//메소드 호출하기
		Class c2 = Class.forName("day6.ReflectTest");
		//String 하나를 매개변수로 가지는 파라미터를 형식을 만든다.
		Class[] parameterTypes = new Class[] {String.class};
		//String을 인자로 받는 생성자에 접근하는 정보를 얻는다.
		Constructor constructor = c2.getConstructor(parameterTypes);
		
		//객체 생성
		Object obj = constructor.newInstance(new Object[] {"REFLECT"}); //
		
		//String 하나를 매개변수로 가ㄴ지는 파라미터 형식을 만든다.
		Class[] parameterTypes2 = new Class[] {String.class};
		//String을 인자로 받는 메소드에 접근하는 정보를 얻는다.
		//메소드 얻기 getMethod(메소드명, 파라미터 타입) : get메소드면 파라미터가 필요없으므로 null을 넣어주기
		Method method = c2.getMethod("test01", parameterTypes2);
		//실제 invoke시 사용할 파라미터 값
		Object[] parameters = new Object[] {"메소드 호출"};
		//메소드를 호출한다.
		//invoke(invoke할 객체, 파라미터)
		Object oResult = method.invoke(obj, parameters);
	}
	
	public void test01(String paramStr) {
		System.out.println("test01 메소드 호출 : " + paramStr);
	}
	public void test02() {
		
	}

}
