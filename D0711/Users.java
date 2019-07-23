package D0711;

import java.util.ArrayList;
import java.util.List;

public class Users implements Cloneable{
	private List<String> userList;
	public Users() {
		userList = new ArrayList();
	}
	
	public Users(List list) {
		this.userList = list;
	}
	
	public void loadData() {
		userList.add("1");
		userList.add("2");
		userList.add("3");
		userList.add("4");
	}
	
	public void print() {
		System.out.println(userList);
	}
	
	public List<String> getUserList(){
		return userList;
	}
	
	public Users Objectclone() throws CloneNotSupportedException{
		List<String> temp = new ArrayList();
		
		for(String s: this.getUserList()) {
			temp.add(s);
		}
		return new Users(temp);
	}
	
	public static void main(String[] args) throws Exception{
		Users originUsers = new Users();
		originUsers.loadData();
		Users cloneUsers = (Users)originUsers.clone();
		cloneUsers.print();
	}
	
}

