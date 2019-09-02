package D0722;

import java.io.File;

public class StreamFileEx2 {

	public static void main(String[] args) {
		File f = new File("C:/Temp");
		
		if(!f.exists() || !f.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0);
		}
		
		File[] files = f.listFiles();
		
		for(int i=0;i<files.length;i++) {
			String fileName = files[i].getName();
			System.out.println(files[i].isDirectory() ? "["+fileName+"]" : fileName);
			//디렉토리(폴더) 인지 확인 -> [디렉토리 명] / 파일 명
		}
	}

}
