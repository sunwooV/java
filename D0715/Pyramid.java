package D0715;

public class Pyramid {

	public static void main(String[] args) {
		new Pyramid();

	}

	
	public Pyramid() {
		int max = 50; //반으로 나눠서 
		for(int i=0;i<max;i++) {
			String print = "";
			for(int j = max;j>0;j--) {
				if(i>=j) {
					print += "*";
				}
				else {
					print += " ";
				}
			}
			for(int j=0;j<max;j++) {
				if(i<=j) {
					print += " ";
					
				}
				else {
					print += "*";
				}
				
			}
			System.out.println(print);
		}
	}
}

