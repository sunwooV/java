package D0717;

import java.util.HashSet;
import java.util.Objects;

public class HashSetEx4 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		HashSet set = new HashSet();
		set.add(new String("abc"));
		set.add(new String("abc"));
		set.add(new Person2("David", 10));
		set.add(new Person2("David", 10));
		
		System.out.println(set);

	}

}

class Person2{
	String name;
	int age;
	Person2(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	//Equals와 hashCode를 하나라도 재정의하지 않으면 중복저장된다.
	
	public boolean equals(Object obj) {
		if(obj instanceof Person2) {
			Person2 tmp = (Person2)obj;
			return name.equals(tmp.name) && age == tmp.age; 
		}
		return false;
	}
	
	public int hashCode() {
		return Objects.hash(name, age);
	}
	
	public String toString() {
		return name + ":" + age;
	}
}
