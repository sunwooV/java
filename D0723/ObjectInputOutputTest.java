package D0723;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;


public class ObjectInputOutputTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream ostream = new FileOutputStream("AaTxtTest.txt");
		ObjectOutputStream p = new ObjectOutputStream(ostream);
		p.writeInt(12345);
		p.writeObject(new Date());
		p.flush();
		ostream.close();
		
		FileInputStream istream = new FileInputStream("AaTxtTest.txt");
		ObjectInputStream p2 = new ObjectInputStream(istream);
		int i = p2.readInt();
		String today = (String)p2.readObject();
		Date date = (Date)p2.readObject();
		
		System.out.println(i);
		System.out.println(today);
		System.out.println(date);
		istream.close();

	}

}
