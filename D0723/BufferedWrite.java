package D0723;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class BufferedWrite {

	public static void main(String[] args){
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;

		Date d = null;
		try {
			//"ReadFile.txt" ������ �д� FileReader ��ü ����
			//BufferedReader ��ü ����
			fr = new FileReader("C:/Temp/HashSetLotto.java");
			br = new BufferedReader(fr);
			
			//FileWriter�� ���� "CopyFile.txt"�� ����Ѵ�. ���� ���Ͽ� �����.
			//BufferedWriter ��ü ����
			fw = new FileWriter("C:/Temp/CopyFile.txt", false); //false : �����
			bw = new BufferedWriter(fw);
			
			String s = null;
			d = new Date();
			
			//���� ���� ���� �ð�
			long start = d.getTime();
//			System.out.println(start);
			
			//ReadFile.txt���� �� �پ� �о BufferedReader�� �����Ѵ�.
			while((s=br.readLine())!=null) {
				//���� ������(����)�� BufferedWriter�� ����.
				//�� �پ� �����Ƿ�, newLine() �޼ҵ�� �ٹٲ��� ���ش�.
				bw.write(s);
				bw.newLine();
			}
			//���� �Ϸ�� �ð��� ��´�.;
			d = new Date();
			long end = d.getTime();
//			System.out.println(end);
			
			System.out.println("���� �ð� : " + (end - start));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//BufferedReader FileReader�� �ݾ��ش�.
			if(br != null) try {br.close();} catch(IOException e) {}
			if(fr != null) try {fr.close();} catch(IOException e) {}
			
			//BufferedWriter FileWriter�� �ݾ��ش�.
			if(bw != null) try {bw.close();} catch(IOException e) {}
			if(fw != null) try {fw.close();} catch(IOException e) {}
		}
	}

}
