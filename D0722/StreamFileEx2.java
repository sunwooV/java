package D0722;

import java.io.File;

public class StreamFileEx2 {

	public static void main(String[] args) {
		File f = new File("C:/Temp");
		
		if(!f.exists() || !f.isDirectory()) {
			System.out.println("��ȿ���� ���� ���丮�Դϴ�.");
			System.exit(0);
		}
		
		File[] files = f.listFiles();
		
		for(int i=0;i<files.length;i++) {
			String fileName = files[i].getName();
			System.out.println(files[i].isDirectory() ? "["+fileName+"]" : fileName);
			//���丮(����) ���� Ȯ�� -> [���丮 ��] / ���� ��
		}
	}

}
