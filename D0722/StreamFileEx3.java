package D0722;

import java.io.File;
import java.util.ArrayList;

public class StreamFileEx3 {
	static int totalFiles = 0;
	static int totalDirs = 0;
	
	public static void main(String[] args) {
		File dir = new File("c:/Temp");
		
		if(!dir.exists() || !dir.isDirectory()) {
			System.out.println("��ȿ���� ���� ���丮�Դϴ�.");
			System.exit(0);
		}
		
		printFileList(dir);
		
		System.out.println();
		System.out.println("�� " + totalFiles + "���� ����");
		System.out.println("�� " + totalDirs + "���� ���丮");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void printFileList(File dir) {
		System.out.println(dir.getAbsolutePath() + " ���丮"); //c:\Temp ���丮
		File[] files = dir.listFiles(); //dir���� ���ϸ��(���丮 ����)
		
		ArrayList subDir = new ArrayList();
		
		//���� ���ϸ�� ���
		for(int i=0;i<files.length;i++) {
			String filename = files[i].getName();
			
			if(files[i].isDirectory()) {
				filename = "["+filename+"]"; //���丮�̸� []�� ǥ��
				subDir.add(i + ""); //String���� ������ֱ� ���� �ڿ� + ""�� �Ѵ�.
			}
			System.out.println(filename);
		}
		
		int dirNum = subDir.size();
		int fileNum = files.length - dirNum; //files.length �� file directory ���Ե� ��
		
		totalFiles += fileNum;
		totalDirs += dirNum;
		
		System.out.println(fileNum + "���� ����, " + dirNum + "���� ���丮");
		System.out.println();
		
		for(int i=0;i<subDir.size();i++) {
			int index = Integer.parseInt((String)subDir.get(i));
			printFileList(files[index]); //���丮�� �Ϳ� ���� ���ȣ��
		}
	}

}
