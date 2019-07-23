package D0717;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;

//HashSet : 중복을 제거할 수 있다.

public class HashSetLotto {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Set set = new HashSet();
		
		for(int i=0;set.size()<6;i++) {
			int num = (int)(Math.random()*45)+1;
			set.add(new Integer(num));
		}
		
		List list = new LinkedList(set); //LinkedList(Collection c)
		Collections.sort(list);
		System.out.println(list);

	}

}
