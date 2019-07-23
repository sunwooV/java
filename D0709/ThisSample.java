package D0709;

public class ThisSample {
	private int x;
	private static int y;
	
	//public void aaa(ThisSample this){
	public void aaa() {
		this.x = 10;
		ThisSample.y = 20;
	}
	
	//public void bbb(ThisSample this, int x, int y){
	public void bbb(int x, int y) {
		this.x = x;
		ThisSample.y = y;
	}
	
	public void ccc(){
		this.x = 30; //eror
		ThisSample.y = 10;
	}


}
