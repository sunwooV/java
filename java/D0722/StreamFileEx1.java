package D0722;

import java.io.File;

public class StreamFileEx1 {

	public static void main(String[] args) {
		File f = new File("C:\\MyProject\\workspace\\JAVA\\src\\day1\\Abst.java");
		String fileName = f.getName();
		int pos = fileName.lastIndexOf("."); //Ȯ���� ������ ��ġ
		
		System.out.println("��θ� ������ �����̸� - " + f.getName()); //Abst.java
		System.out.println("Ȯ���ڸ� ������ �����̸� - " + fileName.substring(0, pos)); //Abst
		System.out.println("Ȯ���� - " + fileName.substring(pos+1)); //java
		
		System.out.println("��θ� ������ �����̸� - " + f.getPath());
		System.out.println("������ ���� ��� - " + f.getAbsolutePath());
		System.out.println("������ ���� �ִ� ���丮 - " + f.getParent()); //C:\\MyProject\\workspace\\JAVA\\src\\day1
		System.out.println();
		
		System.out.println("File.pathSeparator - " + File.pathSeparator); //String ;
		System.out.println("File.pathSeparatorChar - " + File.pathSeparatorChar); //Char ;
		System.out.println("File.separator - " + File.separator); //String \
		System.out.println("File.separatorChar - " + File.separatorChar); //Char \
	}

}
