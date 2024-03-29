package D0708;

public class VarArgsEx {
	public static void main(String[] args) {
		String[] strArr = {"100","200","300"};
		
		System.out.println(concatenate("", "100", "200", "300")); //100200300
		System.out.println(concatenate("-", strArr)); //100-200-300-
		System.out.println(concatenate(",", new String[] {"1","2","3"})); //1,2,3,
		System.out.println("["+concatenate(",", new String[0]) + "]"); //[]
		System.out.println("["+concatenate(",")+"]"); //[]

	}
	
	//가변인자 : 타입... 변수명
	//가변인자는 항상 마지막 매개변수
	
	static String concatenate(String delim, String... args) {
		String result = "";
		
		for(String str:args) {
			result += str + delim;
			
		}
		return result;
	}

}