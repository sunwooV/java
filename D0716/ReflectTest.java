package D0716;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
	public String str;
	
	public ReflectTest(String str) {
		this.str = str;
		System.out.println("REFLECT ������ ȣ��: " + str);
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = Class.forName("day6.ReflectTest");
		System.out.println(c);
		
		Constructor[] ctor = c.getConstructors(); //�����ڸ� ��´�.
		for(int i=0;i<ctor.length;i++) {
			System.out.println("������: "+ctor[i].toString());
		}
		
		Method[] m = c.getMethods(); //�޼ҵ带 ��´�.
		for(int i=0;i<m.length;i++) {
			System.out.println("�޼ҵ�: " +m[i].toString());
		}
		//�޼ҵ� ȣ���ϱ�
		Class c2 = Class.forName("day6.ReflectTest");
		//String �ϳ��� �Ű������� ������ �Ķ���͸� ������ �����.
		Class[] parameterTypes = new Class[] {String.class};
		//String�� ���ڷ� �޴� �����ڿ� �����ϴ� ������ ��´�.
		Constructor constructor = c2.getConstructor(parameterTypes);
		
		//��ü ����
		Object obj = constructor.newInstance(new Object[] {"REFLECT"}); //
		
		//String �ϳ��� �Ű������� �������� �Ķ���� ������ �����.
		Class[] parameterTypes2 = new Class[] {String.class};
		//String�� ���ڷ� �޴� �޼ҵ忡 �����ϴ� ������ ��´�.
		//�޼ҵ� ��� getMethod(�޼ҵ��, �Ķ���� Ÿ��) : get�޼ҵ�� �Ķ���Ͱ� �ʿ�����Ƿ� null�� �־��ֱ�
		Method method = c2.getMethod("test01", parameterTypes2);
		//���� invoke�� ����� �Ķ���� ��
		Object[] parameters = new Object[] {"�޼ҵ� ȣ��"};
		//�޼ҵ带 ȣ���Ѵ�.
		//invoke(invoke�� ��ü, �Ķ����)
		Object oResult = method.invoke(obj, parameters);
	}
	
	public void test01(String paramStr) {
		System.out.println("test01 �޼ҵ� ȣ�� : " + paramStr);
	}
	public void test02() {
		
	}

}
