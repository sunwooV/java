package D0722;

import java.io.File;
import java.util.ArrayList;

public class StreamFileEx3 {
	static int totalFiles = 0;
	static int totalDirs = 0;
	
	public static void main(String[] args) {
		File dir = new File("c:/Temp");
		
		if(!dir.exists() || !dir.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0);
		}
		
		printFileList(dir);
		
		System.out.println();
		System.out.println("총 " + totalFiles + "개의 파일");
		System.out.println("총 " + totalDirs + "개의 디렉토리");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void printFileList(File dir) {
		System.out.println(dir.getAbsolutePath() + " 디렉토리"); //c:\Temp 디렉토리
		File[] files = dir.listFiles(); //dir내부 파일목록(디렉토리 포함)
		
		ArrayList subDir = new ArrayList();
		
		//내부 파일목록 출력
		for(int i=0;i<files.length;i++) {
			String filename = files[i].getName();
			
			if(files[i].isDirectory()) {
				filename = "["+filename+"]"; //디렉토리이면 []로 표시
				subDir.add(i + ""); //String으로 만들어주기 위해 뒤에 + ""를 한다.
			}
			System.out.println(filename);
		}
		
		int dirNum = subDir.size();
		int fileNum = files.length - dirNum; //files.length 는 file directory 포함된 값
		
		totalFiles += fileNum;
		totalDirs += dirNum;
		
		System.out.println(fileNum + "개의 파일, " + dirNum + "개의 디렉토리");
		System.out.println();
		
		for(int i=0;i<subDir.size();i++) {
			int index = Integer.parseInt((String)subDir.get(i));
			printFileList(files[index]); //디렉토리인 것에 대해 재귀호출
		}
	}

}
